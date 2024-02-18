import config from "@/constants/index";
import customFetch from "..";
import {
    CartItemResponse,
    CartItemUpdateOrCreateNew,
} from "@/types/cart/cart.type";

const BASE_URL_CART_API = config.BASE_URL_BFF + "/carts/bff-customer/cart";

export const getDetailCartUser = () => {
    return customFetch<CartItemResponse[]>(BASE_URL_CART_API);
};

export const getCountCurrentCartUser = () => {
    return customFetch<{ count: number }>(BASE_URL_CART_API + "/count");
};

export const addProductToCart = ({
    body,
}: {
    body: CartItemUpdateOrCreateNew;
}) => {
    return customFetch<void>(BASE_URL_CART_API, {
        method: "POST",
        body: JSON.stringify(body),
    });
};

export const addListProductToCart = ({
    body,
}: {
    body: { listItem: CartItemUpdateOrCreateNew[] };
}) => {
    return customFetch<void>(BASE_URL_CART_API + "/multiple", {
        method: "POST",
        body: JSON.stringify(body),
    });
};

export const updateCartItem = ({
    body,
}: {
    body: CartItemUpdateOrCreateNew;
}) => {
    return customFetch<void>(BASE_URL_CART_API, {
        method: "PATCH",
        body: JSON.stringify(body),
    });
};

export const deleteCartItemByProductId = ({
    body,
}: {
    body: { productIds: number[] };
}) => {
    return customFetch<void>(BASE_URL_CART_API, {
        method: "DELETE",
        body: JSON.stringify(body),
    });
};
