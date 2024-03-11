import { MetadataPage } from "..";

export enum OrderStatus {
    PENDING = "pending",
    PENDING_PAYMENT = "pending-payment",
    PAID = "paid",
    ACCEPTED = "accepted",
    PREPARE = "prepare",
    DELIVERY = "delivery",
    SHIPPING = "shipping",
    COMPLETED = "completed",
    REFUND = "refunded",
    REJECTED = "rejected",
}
export enum PaymentMethod {
    BANKING = "BANKING",
    COD = "COD",
}

export enum PaymentStatus {
    PENDING,
    COMPLETED,
    REJECTED,
}

export interface CancelOrderRequest {
    orderId: number;
    reason: string;
}

export interface DetailOrderResponse {
    id: number;
    address: OrderAddressResponse;
    note: string;
    items: OrderItemResponse[];
    discount: number;
    numberItem: number;
    totalPrice: number;
    deliveryFree: number;
    orderStatus: OrderStatus;
    deliveryMethod: string;
    paymentMethod: PaymentMethod;
    paymentStatus: PaymentStatus;
    rejectReason: string;
}

export interface OrderResponse {
    id: number;
    totalPrice: number;
    orderStatus: keyof typeof OrderStatus;
    items: OrderItemResponse[];
}

export interface OrderAddressResponse {
    id: number;
    fullname: string;
    email: string;
    phoneNumber: string;
    addressLine1: string;
    addressLine2: string;
    addressLine3: string;
}

export interface OrderItemResponse {
    id: number;
    productName: string;
    thumbnailUrl: string;
    productPrice: number;
    quantity: number;
    discount: number;
}

export interface OrderRequest {
    fullName: string;
    email: string;
    phoneNumber: string;
    addressLine1: string;
    addressLine2: string;
    addressLine3: string;
    cartItems: OrderItemRequest[];
    couponeCode: string | null;
    discount: number | null;
    totalPrice: number;
    deliveryFee: number;
    deliveryMethod: string;
    note: string;
    paymentMethod: PaymentMethod;
}

export interface OrderItemRequest {
    productId: number;
    productName: string;
    thumbnailUrl: string;
    quantity: number;
    price: number;
    discount: number;
}

export interface ListOrderUserResponse {
    records: OrderResponse[];
    _metadata: MetadataPage;
}

export interface DetailOrderPriceCheckoutResponse {
    totalCostOfGoods: number;
    totalCostProductDiscount: number;
    deliveryFee: number;
    totalCostVoucherDiscount: number;
    totalSave: number;
    totalPrice: number;
}
