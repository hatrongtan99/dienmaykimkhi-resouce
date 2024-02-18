import config from "@/constants";
import customFetch from "..";
import { InfoPriceCurrentCart } from "@/types/cart/priceCart.type";

const BASE_URL_CART_API =
    config.BASE_URL_BFF + "/carts/bff-customer/cart-price";

export const getInfoPriceCurrentCart = () => {
    return customFetch<InfoPriceCurrentCart>(BASE_URL_CART_API);
};
