import { queryOptions } from "@tanstack/react-query";
import { getInfoPriceCurrentCart } from "./priceCart.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { InfoPriceCurrentCart } from "@/types/cart/priceCart.type";

export const getInfoPriceCurrentCartOptions = ({
    ...options
}: CustomUndefinedInitialDataOptions<InfoPriceCurrentCart> = {}) => {
    return queryOptions({
        queryKey: ["info-current-cart"],
        queryFn: getInfoPriceCurrentCart,
        ...options,
    });
};
