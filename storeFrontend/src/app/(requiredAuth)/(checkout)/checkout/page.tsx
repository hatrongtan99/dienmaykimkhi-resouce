"use client";
import { getDetailCartUserOption } from "@/api/cart/cart.queryOptions";
import { createOrder } from "@/api/order/order.api";
import { calcDetailPriceOrderToCheckoutOptions } from "@/api/order/order.queryOptions";
import { paymentOnlineRequest } from "@/api/payment/payment.api";
import AddressCheckout from "@/components/checkout/AddressCheckout";
import CheckOutItem from "@/components/checkout/CheckOutItem";
import HeadCheckout from "@/components/checkout/HeadCheckout";

import VoucherCheckout from "@/components/checkout/VoucherCheckout";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import Button from "@/components/custom/button/Button";
import { CartContext } from "@/context/CartContextProvider";
import { CheckoutContext } from "@/context/checkout/CheckoutContextProvider";
import { OrderRequest, PaymentMethod } from "@/types/orders/orders.type";
import { formatPriceDisplay } from "@/utils";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useContext } from "react";
import { IoIosCheckmark } from "react-icons/io";
import { twMerge } from "tailwind-merge";

const paymentMethods = [PaymentMethod.BANKING, PaymentMethod.COD] as const;
const CheckoutPage = () => {
    const { listDetailCartItem, revalidateDetailCart } =
        useContext(CartContext);
    const { handleChangePaymentMethod, paymentMethod, getCheckoutBody } =
        useContext(CheckoutContext);
    const { data: detailOrderPrice, isLoading: isLoadingOrderPrice } = useQuery(
        calcDetailPriceOrderToCheckoutOptions()
    );

    const { mutateAsync } = useMutation({
        mutationFn: (body: OrderRequest) => createOrder({ body }),
    });

    const handleCreateOrder = async () => {
        try {
            const body = getCheckoutBody();
            body.totalPrice = detailOrderPrice!.totalPrice;
            body.deliveryFee = detailOrderPrice!.deliveryFee;
            const res = await mutateAsync(body);
            if (paymentMethod == PaymentMethod.BANKING) {
                const paymentonlineResponse = await paymentOnlineRequest({
                    orderId: res.id,
                });
                if (paymentonlineResponse) {
                    window.location = paymentonlineResponse.url as any;
                }
            } else {
                alert("order success");
            }
            revalidateDetailCart();
        } catch (error) {
            console.log(error);
        }
    };
    return (
        <section className="container">
            {listDetailCartItem && (
                <>
                    <AddressCheckout />
                    <HeadCheckout />

                    {listDetailCartItem.map((cartItem) => {
                        return (
                            <CheckOutItem
                                checkoutItem={cartItem}
                                key={cartItem.id}
                            />
                        );
                    })}

                    <VoucherCheckout />

                    {/* payment method */}
                    <div className="flex px-6 py-6 mb-1 bg-white items-center">
                        <div className="">
                            <span className="font-normal text-base">
                                Phương thức thanh toán
                            </span>
                        </div>

                        <div className="flex ml-4 space-x-2">
                            {paymentMethods.map((lable) => {
                                return (
                                    <div
                                        className={twMerge(
                                            "px-4 py-2 border border-stone-300 cursor-pointer",
                                            paymentMethod === lable &&
                                                "border-primary-color border-2 relative before:border-r-primary-color before:border-t-transparent before:border-r-[16px] before:border-t-[16px] before:absolute before:bottom-0 before:right-0"
                                        )}
                                        onClick={() =>
                                            handleChangePaymentMethod(lable)
                                        }
                                        key={lable}
                                    >
                                        <IoIosCheckmark
                                            size={18}
                                            color="#fff"
                                            className="absolute bottom-[-4px] right-[-4px]"
                                        />
                                        <span className="text-sm font-medium">
                                            {lable}
                                        </span>
                                    </div>
                                );
                            })}
                        </div>
                    </div>
                    <div className="px-6 py-6 items-end grid md:grid-cols-4 grid-cols-3 bg-white">
                        {isLoadingOrderPrice ? (
                            <div className="md:col-start-4 col-start-3 text-center">
                                <LoadingBubble />
                            </div>
                        ) : (
                            detailOrderPrice && (
                                <>
                                    <div className="md:col-start-4 col-start-3">
                                        <div className="flex items-center mb-5">
                                            <h3 className="font-normal text-sm text-text-light-color ">
                                                Tổng tiền hàng:{" "}
                                            </h3>
                                            <span className="flex-grow text-end">
                                                {formatPriceDisplay(
                                                    detailOrderPrice.totalCostOfGoods
                                                )}
                                            </span>
                                        </div>
                                        <div className="flex items-center mb-5">
                                            <h3 className="font-normal text-sm text-text-light-color ">
                                                Phí vận chuyển:{" "}
                                            </h3>
                                            <span className="text-end flex-grow">
                                                {formatPriceDisplay(
                                                    detailOrderPrice.deliveryFee
                                                )}
                                            </span>
                                        </div>

                                        <div className="flex items-center mb-5">
                                            <h3 className="font-normal text-sm text-text-light-color ">
                                                Tổng cộng Voucher Giảm giá:{" "}
                                            </h3>
                                            <span className="text-end flex-grow">
                                                {formatPriceDisplay(
                                                    detailOrderPrice.totalSave
                                                )}
                                            </span>
                                        </div>

                                        <div className="flex items-center">
                                            <h3 className="font-normal text-sm text-text-light-color">
                                                Tổng thanh toán:{" "}
                                            </h3>
                                            <span className="text-2xl text-primary-color flex-grow text-end">
                                                {formatPriceDisplay(
                                                    detailOrderPrice.totalPrice
                                                )}
                                            </span>
                                        </div>
                                    </div>
                                </>
                            )
                        )}
                    </div>
                    <div className="text-end px-2 py-2 bg-white my-1">
                        <Button
                            variant="primary"
                            as="button"
                            onClick={handleCreateOrder}
                        >
                            Đặt hàng
                        </Button>
                    </div>
                </>
            )}
        </section>
    );
};

export default CheckoutPage;
