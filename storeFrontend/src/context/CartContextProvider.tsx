"use client";

import {
    ReactNode,
    createContext,
    useCallback,
    useContext,
    useEffect,
    useState,
} from "react";

import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
    CartItemResponse,
    CartItemUpdateOrCreateNew,
} from "@/types/cart/cart.type";
import {
    addListProductToCart,
    addProductToCart,
    deleteCartItemByProductId,
    updateCartItem,
} from "@/api/cart/cart.api";
import {
    getCountCurrentCartUserOption,
    getDetailCartUserOption,
} from "@/api/cart/cart.queryOptions";
import { AuthContext } from "./AuthContextProvider";
import {
    KEY_CART_USER,
    addOrUpdateProductIntoCartStorage,
    localStorageUtils,
} from "@/utils/storage";
import { getInfoPriceCurrentCartOptions } from "@/api/cart/priceCartQueryOptions";
import { InfoPriceCurrentCart } from "@/types/cart/priceCart.type";

export const CartContext = createContext(
    {} as {
        countCartItem: number;
        handleAddOrUpdateCartItem: (
            item: CartItemUpdateOrCreateNew,
            type: "add" | "update"
        ) => Promise<void>;
        handleDeleteCartItem: (productIds: number[]) => Promise<void>;
        handleAddProductIdIntoList: (id: number) => void;
        handleClickCheckBoxAll: () => void;
        listProductIds: number[];
        listDetailCartItem: CartItemResponse[] | undefined;
        infoPriceCurrentCart: InfoPriceCurrentCart | undefined;
    }
);

const CartContextProvider = ({ children }: { children: ReactNode }) => {
    const queryClient = useQueryClient();
    const { auth } = useContext(AuthContext);
    const [countCartItem, setcountCartItem] = useState<number>(0);
    const [listProductIds, setListProductIds] = useState<number[]>([]);

    const revalidateDetailCart = () => {
        queryClient.invalidateQueries({
            queryKey: getDetailCartUserOption()["queryKey"],
        });
        queryClient.invalidateQueries({
            queryKey: getInfoPriceCurrentCartOptions()["queryKey"],
        });
    };

    // count item in current cart
    const { data: countResponse, refetch: refetchCountItem } = useQuery(
        getCountCurrentCartUserOption({
            enabled: !!auth,
        })
    );

    // fetch list cartItem;
    const { data: listDetailCartItem } = useQuery(
        getDetailCartUserOption({
            enabled: !!auth,
        })
    );
    // fetch info price current cart
    const { data: infoPriceCurrentCart } = useQuery(
        getInfoPriceCurrentCartOptions({ enabled: !!auth })
    );

    // add all item in local storage if auth is exist
    const { mutateAsync: mutateAsyncAddListItem } = useMutation({
        mutationFn: (listItem: CartItemUpdateOrCreateNew[]) =>
            addListProductToCart({ body: { listItem } }),
        onSuccess: () => {
            revalidateDetailCart();
        },
    });

    // auto insert cart item store in local storage
    useEffect(() => {
        const cartStore = localStorageUtils!.getValue(
            KEY_CART_USER,
            []
        ) as CartItemUpdateOrCreateNew[];

        if (auth) {
            (async function () {
                try {
                    if (cartStore.length > 0) {
                        await mutateAsyncAddListItem(cartStore);
                        localStorageUtils!.deleteKey(KEY_CART_USER);
                    }
                    await refetchCountItem();
                } catch (error) {
                    console.error(error);
                }
            })();
        } else {
            setcountCartItem(cartStore.length);
        }
    }, [auth]);

    // update count state
    useEffect(() => {
        if (countResponse) {
            setcountCartItem(countResponse.count);
        }
    }, [countResponse]);

    // add product
    const { mutateAsync: mutateAsyncAddItem } = useMutation({
        mutationFn: (cartItem: CartItemUpdateOrCreateNew) =>
            addProductToCart({ body: cartItem }),
        onSuccess: () => {
            revalidateDetailCart();
        },
    });

    // update product
    const { mutateAsync: mutateAsyncUpdateItem } = useMutation({
        mutationFn: (cartItem: CartItemUpdateOrCreateNew) =>
            updateCartItem({ body: cartItem }),
        onSuccess: () => {
            revalidateDetailCart();
        },
    });
    const handleAddOrUpdateCartItem = useCallback(
        async (item: CartItemUpdateOrCreateNew, type: "add" | "update") => {
            if (auth) {
                if (type == "add") {
                    await mutateAsyncAddItem(item);
                } else if (type == "update") {
                    await mutateAsyncUpdateItem(item);
                }
                await refetchCountItem();
            } else {
                addOrUpdateProductIntoCartStorage(item, setcountCartItem);
            }
        },
        [auth]
    );

    // delete product
    const { mutateAsync: mutateAsyncDeleteListItem } = useMutation({
        mutationFn: (productIds: number[]) =>
            deleteCartItemByProductId({ body: { productIds } }),
        onSuccess: () => {
            revalidateDetailCart();
        },
    });

    const handleDeleteCartItem = useCallback(
        async (productIds: number[]) => {
            if (auth) {
                await mutateAsyncDeleteListItem(productIds);
                await refetchCountItem();
            } else {
                productIds.forEach((id) => {
                    addOrUpdateProductIntoCartStorage(
                        { productId: id, quantity: 0 },
                        setcountCartItem
                    );
                });
            }
        },
        [auth]
    );

    const handleAddProductIdIntoList = useCallback((id: number) => {
        setListProductIds((prev) => {
            if (prev.includes(id)) {
                return prev.filter((i) => i != id);
            } else {
                return [...prev, id];
            }
        });
    }, []);

    const handleClickCheckBoxAll = () => {
        if (listDetailCartItem) {
            if (listProductIds.length == countCartItem) {
                setListProductIds([]);
            } else {
                // productId
                setListProductIds(listDetailCartItem.map((i) => i.productId));
            }
        }
    };

    return (
        <CartContext.Provider
            value={{
                countCartItem,
                handleAddOrUpdateCartItem,
                handleDeleteCartItem,
                handleAddProductIdIntoList,
                handleClickCheckBoxAll,
                listDetailCartItem,
                listProductIds,
                infoPriceCurrentCart,
            }}
        >
            {children}
        </CartContext.Provider>
    );
};

export default CartContextProvider;
