"use client";
import NoList from "@/components/common/NoList";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import HeadPurchase from "@/components/users/purchase/HeadPurchase";
import PurchaseContainer from "@/components/users/purchase/PurchaseContainer";
import { OrdersContext } from "@/context/users/OrdersContexProvider";
import { getKeyOrderFromSearchParams } from "@/utils";
import { useSearchParams } from "next/navigation";
import { useContext } from "react";

const UserPurchasePage = () => {
    const searchParams = useSearchParams();

    const {
        isLoadingListOrder: isLoading,
        isError,
        getListorderByUserInMap,
        refElementObserver,
    } = useContext(OrdersContext);

    if (isError) {
        throw new Error("some thing wrong");
    }

    const listOrder = getListorderByUserInMap(
        getKeyOrderFromSearchParams(searchParams)
    );

    return (
        <div className="">
            <HeadPurchase />
            <div className="w-full min-h-[400px]">
                {isLoading && !listOrder.length ? (
                    <div className="bg-white flex items-center justify-center rounded-md min-h-[400px]">
                        <LoadingBubble />
                    </div>
                ) : (
                    <>
                        {listOrder.length == 0 ? (
                            <NoList />
                        ) : (
                            listOrder.map((perchase, index) => {
                                return (
                                    <PurchaseContainer
                                        perchase={perchase}
                                        key={perchase.id}
                                        ref={
                                            index == listOrder.length - 1
                                                ? refElementObserver
                                                : null
                                        }
                                    />
                                );
                            })
                        )}
                    </>
                )}

                {isLoading && listOrder.length && (
                    <div className="text-center my-2">
                        <LoadingBubble />
                    </div>
                )}
            </div>
        </div>
    );
};

export default UserPurchasePage;
