export const SORT_QUERY_PRICE_ASC = "price_asc";
export const SORT_QUERY_PRICE_DESC = "price_desc";
export const FILED_FILTER_STRING = [
    "sort",
    "price",
    "filter",
    "brand",
    "orderStatus",
] as const;

export enum OrderStatusFilter {
    ALL = "all",
    PENDING_PAYMENT = "pending-payment",
    DELIVERY = "delivery",
    SHIPPING = "shipping",
    COMPLETED = "completed",
    REJECTED = "rejected",
    REFUNDED = "refunded",
}

export default {
    BASE_URL_BFF: process.env.NEXT_PUBLIC_BASE_URL_API,
    BASE_URL_OAUTH2_CLIENT:
        process.env.NEXT_PUBLIC_BASE_URL_OAUTH2_AUTHENTICATION,
};
