import Image from "next/image";
import Link from "next/link";
import { TiMinus, TiPlus } from "react-icons/ti";
import Button from "../custom/button/Button";
import { MdArrowDropDown } from "react-icons/md";
import { FocusEvent, useContext, useEffect, useState } from "react";
import { CartItemResponse } from "@/types/cart/cart.type";
import { CartContext } from "@/context/CartContextProvider";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { getProductLineCartDetailOption } from "@/api/product/product.queryOption";
import { formatPriceDisplay } from "@/utils";

const CartItem = ({ cartItem }: { cartItem: CartItemResponse }) => {
    const queryClient = useQueryClient();

    const { productId } = cartItem;

    const {
        handleAddOrUpdateCartItem,
        handleDeleteCartItem,
        handleAddProductIdIntoList,
        listProductIds,
    } = useContext(CartContext);

    const [quantity, setQuantity] = useState(cartItem.quantity);

    // update quantity state
    useEffect(() => {
        setQuantity(cartItem.quantity);
    }, [cartItem]);

    const { data: productLineCart } = useQuery(
        getProductLineCartDetailOption({
            productId,
            initialData: queryClient.getQueryData(
                getProductLineCartDetailOption({ productId })["queryKey"]
            ),
            staleTime: 1000 * 60 * 5,
        })
    );

    const updateInputQuantity = async (value: number) => {
        if (value !== cartItem.quantity) {
            if (value == 0) {
                const value = confirm("xoá sản phẩm?");
                if (!value) return;
            }
            try {
                await handleAddOrUpdateCartItem(
                    {
                        productId: cartItem.productId,
                        quantity: value,
                    },
                    "update"
                );
            } catch (error) {
                setQuantity(cartItem.quantity);
                console.log(error);
            }
        }
    };
    const handleBlurUpdateItem = async (e: FocusEvent<HTMLInputElement>) => {
        const value = parseInt(e.target.value || "0");
        await updateInputQuantity(value);
    };

    const hanldeButtonUpdateQuantity = async (
        type: "increase" | "decrease"
    ) => {
        try {
            if (type == "increase") {
                await handleAddOrUpdateCartItem(
                    {
                        productId: cartItem.productId,
                        quantity: cartItem.quantity + 1,
                    },
                    "update"
                );
            } else {
                if (cartItem.quantity - 1 == 0) {
                    const result = confirm("Xoá sản phẩm?");
                    if (!result) return;
                }
                await handleAddOrUpdateCartItem(
                    {
                        productId: cartItem.productId,
                        quantity: cartItem.quantity - 1,
                    },
                    "update"
                );
            }
        } catch (error) {
            console.log(error);
        }
    };

    if (productLineCart) {
        return (
            <div className="flex mb-2 py-3 px-5 pr-2 bg-white">
                <input
                    type="checkbox"
                    checked={listProductIds.includes(productId)}
                    onChange={() => handleAddProductIdIntoList(productId)}
                />
                <div className="w-[40%] ml-4">
                    <Link
                        href={`/product/${productLineCart.slug}`}
                        className="flex items-center"
                    >
                        <div className="w-[100px] h-[100px] relative flex-shrink-0">
                            <Image
                                src={productLineCart.thumbnail}
                                fill
                                alt={productLineCart.name}
                            />
                        </div>
                        <div className="ml-4 text-sm">
                            <h3 className="line-clamp-2">
                                {productLineCart?.name}
                            </h3>
                        </div>
                    </Link>
                </div>

                <div className="flex-grow flex text-sm items-center text-center">
                    <div className="flex-1">
                        <span className="line-through text-stone-400 mr-2">
                            {formatPriceDisplay(299000)}
                        </span>
                        <span className="text-stone-600">
                            {formatPriceDisplay(productLineCart.price)}
                        </span>
                    </div>

                    <div className="flex-1 flex space-x-0.5 justify-center">
                        <span
                            className="inline-block border p-2 active:ring-2 active:ring-stone-200 cursor-pointer rounded-sm"
                            onClick={() =>
                                hanldeButtonUpdateQuantity("decrease")
                            }
                        >
                            <TiMinus />
                        </span>
                        <input
                            type="number"
                            className="w-[50px] border text-center focus:ring outline-none rounded-sm"
                            value={quantity}
                            onChange={(e) =>
                                setQuantity(parseInt(e.target.value))
                            }
                            onBlur={handleBlurUpdateItem}
                            onKeyUp={(e) => {
                                if (e.key == "Enter") {
                                    const value = parseInt(
                                        e.currentTarget.value || "0"
                                    );
                                    updateInputQuantity(value);
                                }
                            }}
                        />
                        <span
                            className="inline-block border p-2 active:ring-2 active:ring-stone-200 cursor-pointer rounded-sm"
                            onClick={() =>
                                hanldeButtonUpdateQuantity("increase")
                            }
                        >
                            <TiPlus />
                        </span>
                    </div>
                    <div className="flex-1 text-red-500">
                        {formatPriceDisplay(
                            productLineCart.price * cartItem.quantity
                        )}
                    </div>

                    <div className="flex-1">
                        <Button
                            variant="text"
                            as={"button"}
                            size="sm"
                            className="text-text-color hover:text-primary-color"
                            onClick={() => handleDeleteCartItem([productId])}
                        >
                            Xoá
                        </Button>
                        <Button
                            variant="text"
                            as={"button"}
                            size="sm"
                            className="text-xs"
                            rightIcon={<MdArrowDropDown size={24} />}
                        >
                            Tìm sản phẩm tương tự
                        </Button>
                    </div>
                </div>
            </div>
        );
    }
};

export default CartItem;
