import { CartItemUpdateOrCreateNew } from "@/types/cart/cart.type";
import { Dispatch, SetStateAction } from "react";

class StorageUtils {
    constructor(private storage: Storage) {}

    public getValue<T>(key: string, defaultValue: T): T {
        const value = this.storage.getItem(key);
        if (value) {
            return JSON.parse(value);
        }
        return defaultValue;
    }

    public setValue<T>(key: string, value: T): void {
        this.storage.setItem(key, JSON.stringify(value));
    }

    public deleteKey(key: string): void {
        this.storage.removeItem(key);
    }

    public clear(): void {
        this.storage.clear();
    }
}

export default StorageUtils;

export let localStorageUtils: StorageUtils | null = null;

if (typeof window !== "undefined") {
    localStorageUtils = new StorageUtils(localStorage);
}

// cart
export const KEY_CART_USER = "carts";

export const addOrUpdateProductIntoCartStorage = (
    product: CartItemUpdateOrCreateNew,
    setcountCartItem?: Dispatch<SetStateAction<number>>
) => {
    let existCart = localStorageUtils!.getValue(
        KEY_CART_USER,
        []
    ) as CartItemUpdateOrCreateNew[];
    let storaged = false;
    if (product.quantity == 0) {
        existCart = existCart.filter(
            (p) => !(p.productId == product.productId)
        );
    } else {
        existCart.forEach((item) => {
            if (item.productId === product.productId) {
                item.quantity += product.quantity;
                storaged = true;
            }
        });
    }
    if (!storaged) {
        existCart.push(product);
    }
    setcountCartItem && setcountCartItem(existCart.length);
    localStorageUtils!.setValue(KEY_CART_USER, existCart);
};
