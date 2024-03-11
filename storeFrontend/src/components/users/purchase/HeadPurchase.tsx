"use client";

import { OrderStatusFilter } from "@/constants";
import { RouterConftext } from "@/context/RouterContextProvider";
import { useSearchParams } from "next/navigation";
import { useContext, useState } from "react";

export const KEY_ORDER_STATUS_FILTER = "orderStatus";

const tagsTypeOrderStatus = [
    {
        type: OrderStatusFilter.ALL,
        lable: "Tất cả",
    },
    {
        type: OrderStatusFilter.PENDING_PAYMENT,
        lable: "Chờ thanh toán",
    },
    {
        type: OrderStatusFilter.DELIVERY,
        lable: "Vận chuyển",
    },
    {
        type: OrderStatusFilter.SHIPPING,
        lable: "Chờ giao hàng",
    },
    {
        type: OrderStatusFilter.COMPLETED,
        lable: "Hoàn thành",
    },
    {
        type: OrderStatusFilter.REJECTED,
        lable: "Đã huỷ",
    },
    {
        type: OrderStatusFilter.REFUNDED,
        lable: "Trả hàng/Hoàn tiền",
    },
];

const HeadPurchase = () => {
    const searchParams = useSearchParams();
    const { setSearchParamsObject } = useContext(RouterConftext);

    const [index, setIndex] = useState(() => {
        const orderStatus = searchParams.get(KEY_ORDER_STATUS_FILTER);
        if (orderStatus) {
            return tagsTypeOrderStatus.findIndex((i) => i.type === orderStatus);
        }
        return 0;
    });

    const handleClickTagFilter = (
        tag: {
            type: OrderStatusFilter;
            lable: string;
        },
        index: number
    ) => {
        setIndex(index);
        setSearchParamsObject((prev) => ({ ...prev, orderStatus: [tag.type] }));
    };

    return (
        <section className="w-full bg-white">
            <div className="flex text-sm h-[40px] border-b-2 border-b-stone-200 relative">
                <div
                    className="absolute w-[calc(100%/7)] bottom-[-2px] left-0 h-1 bg-red-500 transition-all duration-300"
                    style={{ transform: `translateX(${index * 100}%)` }}
                />

                {tagsTypeOrderStatus.map((tag, index) => {
                    return (
                        <div
                            className="flex-1 h-full flex items-center justify-center cursor-pointer"
                            key={index}
                            onClick={() => handleClickTagFilter(tag, index)}
                        >
                            {tag.lable}
                        </div>
                    );
                })}
            </div>
        </section>
    );
};

export default HeadPurchase;
