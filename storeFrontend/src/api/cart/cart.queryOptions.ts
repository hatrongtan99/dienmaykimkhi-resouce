import { queryOptions } from "@tanstack/react-query";
import { getCountCurrentCartUser, getDetailCartUser } from "./cart.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { CartItemResponse } from "@/types/cart/cart.type";

export const getDetailCartUserOption = (
    options?: CustomUndefinedInitialDataOptions<CartItemResponse[]>
) => {
    return queryOptions({
        queryKey: ["detail-cart-user"],
        queryFn: getDetailCartUser,
        ...options,
    });
};

export const getCountCurrentCartUserOption = (
    options?: CustomUndefinedInitialDataOptions<{ count: number }>
) => {
    return queryOptions({
        queryKey: ["count-cart-item-in-cart"],
        queryFn: getCountCurrentCartUser,
        ...options,
    });
};
