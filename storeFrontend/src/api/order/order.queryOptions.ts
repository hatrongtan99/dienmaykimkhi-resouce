import { queryOptions } from "@tanstack/react-query";
import { getListOrderByUser } from "./order.api";
import { CustomUndefinedInitialDataOptions } from "@/types";

export const getListOrderByUserOptions = ({
    params,
    ...options
}: { params?: string } & CustomUndefinedInitialDataOptions<any> = {}) => {
    return queryOptions({
        queryKey: ["get-list-order-by-user", { params }],
        queryFn: () => getListOrderByUser({ params }),
        ...options,
    });
};
