"use client";
import { getListOrderByUserOptions } from "@/api/order/order.queryOptions";
import NoList from "@/components/common/NoList";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import HeadPurchase, {
    KEY_ORDER_STATUS_FILTER,
} from "@/components/users/purchase/HeadPurchase";
import PurchaseContainer from "@/components/users/purchase/PurchaseContainer";
import { OrderStatusFilter } from "@/constants";
import { buildParamsString } from "@/utils";
import { useQuery } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";
import { useState } from "react";

const UserPurchasePage = () => {
    const searchParams = useSearchParams();
    const [page, setPage] = useState(0);

    const {
        data: listOrder,
        isLoading,
        isError,
    } = useQuery(
        getListOrderByUserOptions({
            params: buildParamsString({
                page,
                limit: 8,
                [KEY_ORDER_STATUS_FILTER]:
                    searchParams.get(KEY_ORDER_STATUS_FILTER) ??
                    OrderStatusFilter.ALL,
            }),
            refetchOnWindowFocus: false,
        })
    );

    if (isError) {
        throw new Error("some thing wrong");
    }

    return (
        <div className="">
            <HeadPurchase />
            <div className="w-full min-h-[400px]">
                {isLoading && (
                    <div className="bg-white flex items-center justify-center rounded-md">
                        <LoadingBubble />
                    </div>
                )}
                {listOrder &&
                    (listOrder.records.length == 0 ? (
                        <NoList />
                    ) : (
                        listOrder.records.map((perchase) => {
                            return (
                                <PurchaseContainer
                                    perchase={perchase}
                                    key={perchase.id}
                                />
                            );
                        })
                    ))}
            </div>
        </div>
    );
};

export default UserPurchasePage;
