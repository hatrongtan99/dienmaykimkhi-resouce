import { queryOptions } from "@tanstack/react-query";
import {
    calcDetailPriceOrderToCheckout,
    getDetailOrder,
    getListOrderByUser,
} from "./order.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import {
    DetailOrderPriceCheckoutResponse,
    DetailOrderResponse,
    ListOrderUserResponse,
} from "@/types/orders/orders.type";

export const getListOrderByUserOptions = ({
    params,
    ...options
}: {
    params?: string;
} & CustomUndefinedInitialDataOptions<ListOrderUserResponse> = {}) => {
    return queryOptions({
        queryKey: ["get-list-order-by-user", { params }],
        queryFn: () => getListOrderByUser({ params }),
        ...options,
    });
};

export const getDetailOrderOptions = ({
    orderId,
    ...options
}: {
    orderId: number;
} & CustomUndefinedInitialDataOptions<DetailOrderResponse>) => {
    return queryOptions({
        queryKey: ["get-detail-order-by-user", { orderId }],
        queryFn: () => getDetailOrder({ orderId }),
        ...options,
    });
};

export const calcDetailPriceOrderToCheckoutOptions = ({
    params,
    ...options
}: {
    params: string;
} & CustomUndefinedInitialDataOptions<DetailOrderPriceCheckoutResponse> = {}) => {
    return queryOptions({
        queryKey: ["detail-price-checkout-order", { params }],
        queryFn: () => calcDetailPriceOrderToCheckout({ params }),
        ...options,
    });
};
