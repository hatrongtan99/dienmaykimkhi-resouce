import Image from "next/image";
import Link from "next/link";
import { TiMinus, TiPlus } from "react-icons/ti";
import Button from "../custom/button/Button";
import { MdArrowDropDown } from "react-icons/md";
import { FocusEvent, useContext, useEffect, useState } from "react";
import { CartItemResponse } from "@/types/cart/cart.type";
import { CartContext } from "@/context/CartContextProvider";
import { useQueryClient } from "@tanstack/react-query";
import { formatPriceDisplay } from "@/utils";

const CartItem = ({ cartItem }: { cartItem: CartItemResponse }) => {
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

    return (
        <div className="flex mb-2 py-3 px-5 pr-2 bg-white">
            <input
                type="checkbox"
                checked={listProductIds.includes(cartItem.productId)}
                onChange={() => handleAddProductIdIntoList(cartItem.productId)}
            />
            <div className="w-[40%] ml-4">
                <Link
                    href={`/product/${cartItem.slug}`}
                    className="flex items-center"
                >
                    <div className="w-[100px] h-[100px] relative flex-shrink-0">
                        <Image
                            src={cartItem.thumbnailUrl}
                            fill
                            alt={cartItem.productName}
                        />
                    </div>
                    <div className="ml-4 text-sm">
                        <h3 className="line-clamp-2">{cartItem.productName}</h3>
                    </div>
                </Link>
            </div>

            <div className="flex-grow flex text-sm items-center text-center">
                <div className="flex-1">
                    <span className="line-through text-stone-400 mr-2">
                        {formatPriceDisplay(299000)}
                    </span>
                    <span className="text-stone-600">
                        {formatPriceDisplay(cartItem.price)}
                    </span>
                </div>

                <div className="flex-1 flex space-x-0.5 justify-center">
                    <span
                        className="inline-block border p-2 active:ring-2 active:ring-stone-200 cursor-pointer rounded-sm"
                        onClick={() => hanldeButtonUpdateQuantity("decrease")}
                    >
                        <TiMinus />
                    </span>
                    <input
                        type="number"
                        className="w-[50px] border text-center focus:ring outline-none rounded-sm"
                        value={quantity}
                        onChange={(e) => setQuantity(parseInt(e.target.value))}
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
                        onClick={() => hanldeButtonUpdateQuantity("increase")}
                    >
                        <TiPlus />
                    </span>
                </div>
                <div className="flex-1 text-red-500">
                    {formatPriceDisplay(cartItem.price * cartItem.quantity)}
                </div>

                <div className="flex-1">
                    <Button
                        variant="text"
                        as={"button"}
                        size="sm"
                        className="text-text-color hover:text-primary-color"
                        onClick={() =>
                            handleDeleteCartItem([cartItem.productId])
                        }
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
};

export default CartItem;
