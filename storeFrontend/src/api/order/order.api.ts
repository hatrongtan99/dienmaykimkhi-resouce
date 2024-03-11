import config from "@/constants";
import customFetch from "..";
import {
    CancelOrderRequest,
    DetailOrderPriceCheckoutResponse,
    DetailOrderResponse,
    ListOrderUserResponse,
    OrderRequest,
    OrderResponse,
} from "@/types/orders/orders.type";

const BASE_URL_ORDER_API = config.BASE_URL_BFF + "/orders/bff-customer/orders";

export const getListOrderByUser = ({ params }: { params?: string }) => {
    return customFetch<ListOrderUserResponse>(
        BASE_URL_ORDER_API + `?${params}`
    );
};

export const getDetailOrder = ({ orderId }: { orderId: number }) => {
    return customFetch<DetailOrderResponse>(BASE_URL_ORDER_API + `/${orderId}`);
};

export const createOrder = ({ body }: { body: OrderRequest }) => {
    return customFetch<OrderResponse>(BASE_URL_ORDER_API, {
        method: "post",
        body: JSON.stringify(body),
    });
};

export const cancelOrder = ({ body }: { body: CancelOrderRequest }) => {
    return customFetch<void>(BASE_URL_ORDER_API + "/cancel", {
        method: "post",
        body: JSON.stringify(body),
    });
};

export const calcDetailPriceOrderToCheckout = ({
    params,
}: {
    params: string;
}) => {
    return customFetch<DetailOrderPriceCheckoutResponse>(
        BASE_URL_ORDER_API + "/price-checkout" + `?${params}`
    );
};
