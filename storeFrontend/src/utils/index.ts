import { KEY_ORDER_STATUS_FILTER } from "@/components/users/purchase/HeadPurchase";
import { OrderStatusFilter } from "@/constants";
import { PageableParams } from "@/types";
import { ReadonlyURLSearchParams } from "next/navigation";

export const buildParamsString = (
    object: { [key: string]: string | number } & PageableParams = {},
    initSearchParams: string = ""
): string => {
    let params = new URLSearchParams(initSearchParams);

    Object.keys(object).forEach((key) => {
        const value = object[key].toString();
        if (value == "") {
            params.delete(key);
        } else {
            params.set(key, object[key].toString());
        }
    });

    return params.toString();
};

export const formatPriceDisplay = (price: number) => {
    return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
    }).format(price);
};

export const getKeyOrderFromSearchParams = (
    searchParams: ReadonlyURLSearchParams
) => {
    return (searchParams.get(KEY_ORDER_STATUS_FILTER) ??
        OrderStatusFilter.ALL) as OrderStatusFilter;
};
