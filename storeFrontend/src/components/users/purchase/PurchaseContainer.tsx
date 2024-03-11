import React from "react";
import PurchaseItem from "./PurchaseItem";
import { formatPriceDisplay } from "@/utils";
import Button from "@/components/custom/button/Button";
import { MdOutlineLocalShipping } from "react-icons/md";
import {
    CancelOrderRequest,
    OrderResponse,
    OrderStatus,
} from "@/types/orders/orders.type";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { cancelOrder } from "@/api/order/order.api";
import { getListOrderByUserOptions } from "@/api/order/order.queryOptions";

const HeadStatusOrder = (orderStatus: keyof typeof OrderStatus) => {
    let message = "",
        status = "";

    switch (orderStatus) {
        case "ACCEPTED": {
            message = "Đơn hàng đã được xác nhận";
            status = "VẬN CHUYỂN";
            break;
        }
        case "COMPLETED": {
            message = "Đơn hàng đã được giao thành công";
            status = "HOÀN THÀNH";
            break;
        }
        case "DELIVERY": {
            message = "Đơn hàng đang được giao cho đơn vị vận chuyển";
            status = "VẬN CHUYỂN";
            break;
        }
        case "PAID": {
            message = "Đơn hàng đã được thanh toán";
            status = "VẬN CHUYỂN";
            break;
        }
        case "PENDING": {
            message = "Đơn hàng đang được chờ xác nhận";
            status = "CHƯA XÁC NHẬN";
            break;
        }
        case "PENDING_PAYMENT": {
            message = "Đơn hàng đang được chờ thanh toán";
            status = "VẬN CHUYỂN";
            break;
        }
        case "PREPARE": {
            message = "Đơn hàng đang được chuẩn bị";
            status = "VẬN CHUYỂN";
            break;
        }
        case "REFUND": {
            message = "Đơn hàng được hoàn trả";
            status = "TRẢ HÀNG / HOÀN TIỀN";
            break;
        }
        case "SHIPPING": {
            message = "Đơn hàng đang được giao đến bạn";
            status = "VẬN CHUYỂN";
            break;
        }
        default: {
        }
    }
    let body = (
        <>
            <div className="flex items-center text-teal-600">
                <MdOutlineLocalShipping className="mx-2" />
                <span className="text-sm">{message}</span>
            </div>
            <span className="mx-2 inline-block border-l h-[24px]"></span>
            <span className="uppercase text-base font-medium text-primary-color">
                {status}
            </span>
        </>
    );
    if (orderStatus == "REJECTED") {
        body = (
            <span className="uppercase text-base font-medium text-primary-color">
                ĐÃ HUỶ
            </span>
        );
    }
    return body;
};

const PurchaseContainer = ({ perchase }: { perchase: OrderResponse }) => {
    const queryClient = useQueryClient();

    const { mutateAsync } = useMutation({
        mutationFn: (body: CancelOrderRequest) => cancelOrder({ body }),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [getListOrderByUserOptions()["queryKey"][0]],
                exact: false,
            });
        },
    });

    const handleCancelOrder = async () => {
        try {
            await mutateAsync({ orderId: perchase.id, reason: "" });
        } catch (error) {
            console.log(error);
        }
    };

    const buttonAction = () => {
        const status = perchase.orderStatus;
        const statusIsCancelable: (keyof typeof OrderStatus)[] = [
            "ACCEPTED",
            "PAID",
            "PENDING",
            "PENDING_PAYMENT",
            "PREPARE",
        ];
        if (statusIsCancelable.includes(status)) {
            return (
                <Button size="md" as="button" onClick={handleCancelOrder}>
                    Huỷ đơn hàng
                </Button>
            );
        } else if (status == "COMPLETED") {
            return (
                <Button size="md" as="button">
                    Đánh giá
                </Button>
            );
        }
        return null;
    };
    return (
        <div className="my-4">
            <div className="rounded-b-md overflow-hidden bg-white px-8 py-8">
                <div className="border-b pb-2 text-end">
                    <div className="inline-flex items-center">
                        {HeadStatusOrder(perchase.orderStatus)}
                    </div>
                </div>
                <div className="mt-2">
                    {perchase.items.map((item) => (
                        <PurchaseItem orderItem={item} key={item.id} />
                    ))}
                </div>
            </div>
            <div className="rounded-t-md text-end mt-0.5 bg-white px-6 py-8 relative">
                <div className="inline-flex items-center ">
                    <span className="mr-4 text-sm font-normal">
                        Thành tiền:
                    </span>
                    <span className="text-xl text-red-500 font-medium">
                        {formatPriceDisplay(perchase.totalPrice)}
                    </span>
                </div>
                <div className="absolute top-1/2 -translate-y-1/2 left-1/2 -translate-x-1/2">
                    {buttonAction()}
                </div>
            </div>
        </div>
    );
};

export default PurchaseContainer;
