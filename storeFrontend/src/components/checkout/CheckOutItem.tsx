"use client";

import { CartItemResponse } from "@/types/cart/cart.type";
import { formatPriceDisplay } from "@/utils";
import Image from "next/image";
import Link from "next/link";

const CheckOutItem = ({ checkoutItem }: { checkoutItem: CartItemResponse }) => {
    return (
        <>
            <div className="flex my-1 bg-white px-6 py-6 items-center">
                <div className="w-[50%] lg:w-[40%]">
                    <Link
                        href={`/product/${checkoutItem.slug}`}
                        className="flex items-center"
                    >
                        <div className="w-[60px] h-[60px] relative flex-shrink-0">
                            <Image
                                src={checkoutItem.thumbnailUrl}
                                fill
                                alt={checkoutItem.productName}
                            />
                        </div>
                        <div className="ml-4 text-sm font-normal">
                            <h3 className="line-clamp-1">
                                {checkoutItem.productName}
                            </h3>
                        </div>
                    </Link>
                </div>
                <div className="lg:flex-[2] w-6"></div>
                <div className="flex-1 text-center text-sm font-medium">
                    {formatPriceDisplay(checkoutItem.price)}
                </div>
                <div className="flex-1 text-center text-sm font-medium">
                    {checkoutItem.quantity}
                </div>
                <div className="flex-[2] text-right text-sm font-semibold">
                    {formatPriceDisplay(
                        checkoutItem.price * checkoutItem.quantity
                    )}
                </div>
            </div>
        </>
    );
};

export default CheckOutItem;
