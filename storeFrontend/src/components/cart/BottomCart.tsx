"use client";

import { useContext, useEffect, useRef } from "react";
import Button from "../custom/button/Button";
import { formatPriceDisplay } from "@/utils";
import { CartContext } from "@/context/CartContextProvider";
import PopoverWraper from "../common/PopoverWraper";
import PopoverContentBottomCart from "./PopoverContentBottomCart";
import { useRouter } from "next/navigation";

const BottomCart = () => {
    const router = useRouter();
    const {
        listProductIds,
        countCartItem,
        handleDeleteCartItem,
        handleClickCheckBoxAll,
        infoPriceCurrentCart,
    } = useContext(CartContext);

    const refBox = useRef<HTMLDivElement>(null);

    // sticky detect box
    useEffect(() => {
        if (refBox) {
            const observer = new IntersectionObserver(
                ([e]) => {
                    e.target.classList.toggle("shadow-top", !e.isIntersecting);
                },
                { threshold: 1 }
            );
            observer.observe(refBox.current as Element);
        }
    }, []);

    const handleDeleteButton = async () => {
        const res = confirm("Xoá ?");
        if (!res) return;
        await handleDeleteCartItem(listProductIds);
    };

    const handleClickBuyButton = () => {
        router.push("/checkout");
    };

    console.log(infoPriceCurrentCart);

    return (
        <div
            className="bg-white my-6 py-5 px-5 flex items-center bottom-[-1px] sticky z-10 "
            ref={refBox}
        >
            {infoPriceCurrentCart ? (
                <>
                    <div className="flex-1">
                        <input
                            id="checl-all"
                            className=""
                            type="checkbox"
                            checked={listProductIds.length === countCartItem}
                            onChange={handleClickCheckBoxAll}
                        />
                        <label
                            htmlFor="checl-all"
                            className="ml-2 cursor-pointer"
                        >
                            Chọn Tất Cả ({countCartItem})
                        </label>

                        <Button
                            variant="text"
                            as="button"
                            size="sm"
                            className="min-w-0 ml-2"
                            style={{ background: "none" }}
                            onClick={handleDeleteButton}
                            disable={listProductIds.length == 0}
                        >
                            Xoá ({listProductIds.length})
                        </Button>
                    </div>
                    <div className="mx-4">
                        <PopoverWraper
                            popOverContent={<PopoverContentBottomCart />}
                            stylePopContent="bottom-[100%] left-auto right-0 origin-[center_bottom]"
                        >
                            <div className="flex">
                                <div>Tổng thanh toán ({countCartItem}):</div>
                                <div className="ml-2 text-center text-2xl text-primary-color font-semibold">
                                    {formatPriceDisplay(
                                        infoPriceCurrentCart.totalPrice
                                    )}
                                </div>
                            </div>
                        </PopoverWraper>
                    </div>
                    <Button
                        variant="primary"
                        className="text-xs ml-auto"
                        size="lg"
                        as="button"
                        onClick={handleClickBuyButton}
                    >
                        Mua Hàng
                    </Button>
                </>
            ) : null}
        </div>
    );
};

export default BottomCart;
