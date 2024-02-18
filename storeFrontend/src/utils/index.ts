import { PageableParams } from "@/types";

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
