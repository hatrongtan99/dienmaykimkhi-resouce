"use client";

import { CartContext } from "@/context/CartContextProvider";
import { formatPriceDisplay } from "@/utils";
import { useContext } from "react";

const PopoverContentBottomCart = () => {
    const { infoPriceCurrentCart } = useContext(CartContext);
    return (
        <div className="p-6 w-[600px] h-fit">
            {infoPriceCurrentCart && (
                <>
                    <h4 className="text-lg font-bold pb-4 border-b">
                        Chi tiết khuyến mãi{" "}
                    </h4>

                    <div className="py-3 border-b">
                        <div className="flex justify-between items-center">
                            <span className="text-sm text-text-light-color leading-7">
                                Tổng tiền hàng:
                            </span>
                            <span className="font-medium text-sm">
                                {" "}
                                {formatPriceDisplay(
                                    infoPriceCurrentCart.totalCostOfGoods
                                )}
                            </span>
                        </div>
                    </div>

                    <div className="border-b py-3">
                        <div className="flex justify-between items-center">
                            <span className="text-sm text-text-light-color leading-7">
                                Voucher giảm giá:
                            </span>
                            <span className="font-medium text-sm">
                                {" "}
                                -{formatPriceDisplay(0)}
                            </span>
                        </div>
                        <div className="flex justify-between items-center">
                            <span className="text-sm text-text-light-color leading-7">
                                Giảm giá sản phẩm:
                            </span>
                            <span className="font-medium text-sm">
                                {" "}
                                -
                                {formatPriceDisplay(
                                    infoPriceCurrentCart.totalCostProductDiscount
                                )}
                            </span>
                        </div>
                    </div>

                    <div className="py-3">
                        <div className="flex justify-between items-center">
                            <span className="text-sm font-medium leading-7">
                                Tiết kiệm:
                            </span>
                            <span className="text-primary-color font-medium text-sm">
                                {" "}
                                -
                                {formatPriceDisplay(
                                    infoPriceCurrentCart.totalSave
                                )}
                            </span>
                        </div>
                        <div className="flex justify-between items-center">
                            <span className="text-sm font-medium leading-7">
                                Tổng số tiền:
                            </span>
                            <span className="font-medium text-sm">
                                {" "}
                                {formatPriceDisplay(
                                    infoPriceCurrentCart.totalPrice
                                )}
                            </span>
                        </div>
                        <div className="float-right text-xs font-light text-text-light-color">
                            Số tiền cuối cùng thanh toán
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default PopoverContentBottomCart;
