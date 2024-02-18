"use client";
import { getListOrderByUserOptions } from "@/api/order/order.queryOptions";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import HeadPurchase from "@/components/users/purchase/HeadPurchase";
import PurchaseContainer from "@/components/users/purchase/PurchaseContainer";
import { useQuery } from "@tanstack/react-query";

const UserPurchasePage = () => {
    const { data, isLoading, isError } = useQuery(
        getListOrderByUserOptions({
            refetchOnWindowFocus: false,
        })
    );

    if (isError) {
        throw new Error("some thing wrong");
    }
    if (isLoading) {
        return (
            <div className="w-full min-h-[400px] bg-white flex items-center justify-center rounded-md">
                <LoadingBubble />
            </div>
        );
    }
    return (
        <div className="">
            <HeadPurchase />
            <PurchaseContainer />
            <PurchaseContainer />
            <PurchaseContainer />
            <PurchaseContainer />
            <PurchaseContainer />
            <PurchaseContainer />
        </div>
    );
};

export default UserPurchasePage;
