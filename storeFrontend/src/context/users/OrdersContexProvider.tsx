"use client";
import { getListOrderByUserOptions } from "@/api/order/order.queryOptions";
import { KEY_ORDER_STATUS_FILTER } from "@/components/users/purchase/HeadPurchase";
import { OrderStatusFilter } from "@/constants";
import useIntersectionObserver from "@/hook/useIntersectionObserver";
import {
    ListOrderUserResponse,
    OrderResponse,
    OrderStatus,
} from "@/types/orders/orders.type";
import { buildParamsString, getKeyOrderFromSearchParams } from "@/utils";
import { useQuery } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";
import {
    ReactNode,
    createContext,
    useCallback,
    useEffect,
    useState,
} from "react";

export const OrdersContext = createContext(
    {} as {
        isLoadingListOrder: boolean;
        isError: boolean;
        getListorderByUserInMap: (key?: OrderStatusFilter) => OrderResponse[];
        refElementObserver: <T extends HTMLElement | null>(node: T) => void;
        handleUpdateWhenCancelOrderByIdInTable: (orderId: number) => void;
    }
);

const OrdersContexProvider = ({ children }: { children: ReactNode }) => {
    const searchParams = useSearchParams();
    const [orderTable, setOrderTable] = useState<
        Map<OrderStatusFilter, OrderResponse[]>
    >(new Map());

    const getListorderByUserInMap = useCallback(
        (key: OrderStatusFilter = OrderStatusFilter.ALL) => {
            return orderTable.get(key) ?? [];
        },
        [orderTable]
    );

    const [page, setPage] = useState(0);

    useEffect(() => {
        setPage(0);
    }, [searchParams]);

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
                    getKeyOrderFromSearchParams(searchParams),
            }),
            refetchOnWindowFocus: false,
        })
    );

    const { refElementObserver } = useIntersectionObserver({
        isLoading,
        hasMore: listOrder
            ? listOrder._metadata.page + 1 < listOrder._metadata.pageCount
            : false,
        callback: () => {
            setPage((prev) => prev + 1);
        },
    });
    const [loadedPages, setLoadedPage] = useState<{
        [key: string]: Set<number>;
    }>({});

    useEffect(() => {
        if (listOrder) {
            const key = getKeyOrderFromSearchParams(searchParams);
            if (!loadedPages[key]?.has(page)) {
                setOrderTable((prev) => {
                    prev.set(key, [
                        ...(prev.get(key) ?? []),
                        ...listOrder.records,
                    ]);
                    return structuredClone(prev);
                });
                setLoadedPage((prev) => {
                    prev[key]
                        ? prev[key].add(page)
                        : (prev[key] = new Set([page]));
                    return prev;
                });
            }
        }
    }, [listOrder, page]);

    const handleUpdateWhenCancelOrderByIdInTable = useCallback(
        (orderId: number) => {
            setOrderTable((prev) => {
                const key = getKeyOrderFromSearchParams(searchParams);
                const listOrder = prev.get(key) ?? [];
                const orderCancel = listOrder.find(
                    (order) => order.id == orderId
                );
                if (orderCancel) {
                    orderCancel.orderStatus = "REJECTED";
                }
                return structuredClone(prev);
            });
        },
        [orderTable]
    );

    return (
        <OrdersContext.Provider
            value={{
                isLoadingListOrder: isLoading,
                isError,
                getListorderByUserInMap,
                refElementObserver,
                handleUpdateWhenCancelOrderByIdInTable,
            }}
        >
            {children}
        </OrdersContext.Provider>
    );
};

export default OrdersContexProvider;
