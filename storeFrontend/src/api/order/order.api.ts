import config from "@/constants";
import customFetch from "..";

const BASE_URL_ORDER_API = config.BASE_URL_BFF + "/orders/bff-customer/orders";

export const getListOrderByUser = ({ params }: { params?: string }) => {
    return customFetch(BASE_URL_ORDER_API + `?${params}`);
};
