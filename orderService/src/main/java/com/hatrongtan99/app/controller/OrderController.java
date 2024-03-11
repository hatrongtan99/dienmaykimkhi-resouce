package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.order.*;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.dto.priceDto.PriceOrderResponseDto;
import com.hatrongtan99.app.entity.OrderEntity;
import com.hatrongtan99.app.services.IOrderService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/bff-customer/orders", "/bff-admin/orders"})
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<ListOrderByUserWithPageDto> getListOrder(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit,
            @RequestParam(name = "orderStatus", required = false) String orderStatus
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        Pageable pageable = PageRequest.of(page, limit);
        Page<OrderEntity> resultPage = this.orderService.getOrdersByUserId(userId, pageable, orderStatus);
        MetadataDto metadataDto = MetadataDto.mapToDto(resultPage);
        List<OrderByUserResponseDto> records = null;

        records = this.getRecordsFromPageContent(resultPage.getContent(), (contentItem) -> {
            List<OrderItemResponseDto> items = contentItem.getOrderItemList().stream().map(OrderItemResponseDto::mapToDto).toList();
            return new OrderByUserResponseDto(
                    contentItem.getId(),
                    contentItem.getTotalPrice(),
                    contentItem.getOrderStatus(),
                    items
            );
        });
        return ResponseEntity.ok(new ListOrderByUserWithPageDto(records, metadataDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DetailOrderResponseDto> getDetailOrderOfUserById(
        @PathVariable Long orderId
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        OrderEntity order = this.orderService.getDetailOrder(userId, orderId);
        return ResponseEntity.ok(DetailOrderResponseDto.mapToDto(order));
    }

    @GetMapping("/price-checkout")
    public ResponseEntity<PriceOrderResponseDto> calcPriceOrderCheckout(
            @RequestParam(value = "vouchers", required = false) String vouchers
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        return ResponseEntity.ok(this.orderService.calcPriceOrder(userId, vouchers));
    }

    @PostMapping
    public ResponseEntity<OrderByUserResponseDto> createOrder(
            @RequestBody OrderSaveDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        OrderEntity order = this.orderService.createOrder(userId, body);
        List<OrderItemResponseDto> items = order.getOrderItemList().stream().map(OrderItemResponseDto::mapToDto).toList();
        return ResponseEntity.ok(new OrderByUserResponseDto(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                items
        ));
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelOrder(
            @Valid @RequestBody CancelOrderRequestDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.orderService.cancelOrder(userId, body);
        return ResponseEntity.noContent().build();
    }

    private <T, K> List<T> getRecordsFromPageContent(List<K> iterator, Function<K, T> function) {
        List<T> result = new ArrayList<>();
        for (K contentItem : iterator) {
            result.add(function.apply(contentItem));
        }
        return result;
    }
}
