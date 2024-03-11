package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.order.OrderItemRequestDto;
import com.hatrongtan99.app.dto.inventory.StockAdjustQuantityDto;
import com.hatrongtan99.app.dto.inventory.StockUpdateWhenOrderDto;
import com.hatrongtan99.app.dto.order.CancelOrderRequestDto;
import com.hatrongtan99.app.dto.order.OrderSaveDto;
import com.hatrongtan99.app.dto.priceDto.PriceOrderResponseDto;
import com.hatrongtan99.app.dto.priceDto.TotalPriceCartResponseDto;
import com.hatrongtan99.app.dto.stock.ProductInStockResponseDto;
import com.hatrongtan99.app.entity.OrderAddressEntity;
import com.hatrongtan99.app.entity.OrderEntity;
import com.hatrongtan99.app.entity.OrderItemEntity;
import com.hatrongtan99.app.exceptions.BadRequestException;
import com.hatrongtan99.app.exceptions.NotFoundException;
import com.hatrongtan99.app.messageBroker.publisher.InventoryMessageService;
import com.hatrongtan99.app.repository.OrderRepository;
import com.hatrongtan99.app.services.ICartService;
import com.hatrongtan99.app.services.IInventoryService;
import com.hatrongtan99.app.services.IOrderService;
import com.hatrongtan99.enumeration.cart.CartStatus;
import com.hatrongtan99.enumeration.order.OrderStatus;
import com.hatrongtan99.enumeration.order.PaymentStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final IInventoryService inventoryService;
    private final ICartService cartService;
    private final InventoryMessageService inventoryMessageService;
    @Getter
    private final String defaultMessage = "user cancel order";
    @Override
    @Transactional
    public OrderEntity createOrder(Long userId, OrderSaveDto body) {
        if (body.cartItems().isEmpty()) {
            throw new BadRequestException("order item is empty");
        }

        OrderEntity mainOrder = OrderEntity.builder()
                .userId(userId)
                .note(body.note())
                .couponCode(body.couponCode())
                .discount(body.discount())
                .totalPrice(body.totalPrice())
                .deliveryFee(body.deliveryFee())
                .deliveryMethod(body.deliveryMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .orderStatus(OrderStatus.PENDING)
                .paymentMethod(body.paymentMethod())
                .build();

        OrderAddressEntity address = OrderAddressEntity.builder()
                .orderId(mainOrder)
                .fullname(body.fullName())
                .email(body.email())
                .phoneNumber(body.phoneNumber())
                .addressLine1(body.addressLine1())
                .addressLine2(body.addressLine2())
                .addressLine3(body.addressLine3())
                .build();

        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (OrderItemRequestDto cartItem : body.cartItems()) {
            ProductInStockResponseDto productInStock = this.inventoryService.getQuantityInStock(cartItem.productId());
            assert (productInStock != null);
            if (productInStock.quantity() >= cartItem.quantity()) {
                OrderItemEntity orderItem = OrderItemEntity.builder()
                        .orderId(mainOrder)
                        .productPrice(cartItem.price())
                        .productId(cartItem.productId())
                        .productName(cartItem.productName())
                        .thumbnailUrl(cartItem.thumbnailUrl())
                        .quantity(cartItem.quantity())
                        .discount(cartItem.discount())
                        .build();
                orderItems.add(orderItem);
            } else {
                throw new BadRequestException("product " + cartItem.productId() + " out of stock, please try again");
            }
        }

        mainOrder.setOrderItemList(orderItems);
        mainOrder.setAddressId(address);
        OrderEntity orderSave = this.orderRepository.saveAndFlush(mainOrder);
        // TODO: change status cart
        this.cartService.changeCartStatus(CartStatus.PROCESSED);
        // TODO: minus quantity in stock
        List<StockAdjustQuantityDto> listProductUpdate = body.cartItems().stream().map(i -> new StockAdjustQuantityDto(i.productId(), i.quantity(), null)).toList();
        this.inventoryMessageService.pubChangeStatusInStock(new StockUpdateWhenOrderDto(listProductUpdate, OrderStatus.PENDING));
        return orderSave;
    }

    @Override
    public OrderEntity getDetailOrder(Long userId, Long id) {
        return this.orderRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public Page<OrderEntity> getOrdersByUserId(Long userId, Pageable pageable, String orderStatus) {
        OrderStatus orderStatusEnum = null;
        if (orderStatus != null && !orderStatus.equals("all")) {
            orderStatusEnum = OrderStatus.getByName(orderStatus);
        }
        return this.orderRepository.findByUserIdAndStatusFilter(userId, orderStatusEnum, pageable);
    }

    @Override
    public void cancelOrder(Long userId, CancelOrderRequestDto requestDto) {
        OrderEntity order = this.orderRepository.findByIdAndUserId(requestDto.orderId(), userId)
                .orElseThrow(() -> new NotFoundException("not found"));

        if (requestDto.reason() == null) {
            order.setRejectReason(this.defaultMessage);
        }
        order.setOrderStatus(OrderStatus.REJECTED);
        order.setRejectReason(requestDto.reason());
        this.orderRepository.saveAndFlush(order);
        // TODO: compensation quantity product in stock
        List<StockAdjustQuantityDto> listProductCancel = order.getOrderItemList().stream().map(p -> new StockAdjustQuantityDto(p.getProductId(), p.getQuantity(), null)).toList();
        this.inventoryMessageService.pubChangeStatusInStock(new StockUpdateWhenOrderDto(listProductCancel, order.getOrderStatus()));
    }

    @Override
    public PriceOrderResponseDto calcPriceOrder(Long userId, String vouchers) {
        TotalPriceCartResponseDto detailPriceCartUser = this.cartService.getDetailPriceCartUser(userId);
        assert (detailPriceCartUser != null);
        double deliveryFee = 30000;
        double totalVoucher = 0;
        double totalSave = 0;
        if (vouchers != null) {
            String[] listVoucher = vouchers.split(",");

        }
        totalSave = totalVoucher + detailPriceCartUser.totalCostProductDiscount();
        return new PriceOrderResponseDto(
                detailPriceCartUser.totalCostOfGoods(),
                detailPriceCartUser.totalCostProductDiscount(),
                deliveryFee,
                totalVoucher,
                totalSave,
                detailPriceCartUser.totalCostOfGoods() - totalVoucher + deliveryFee
        );
    }
}
