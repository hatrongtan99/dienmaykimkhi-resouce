import { queryOptions } from "@tanstack/react-query";
import { getDetailOrCreateUser } from "./userInfo.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { UserInfoResponse, UserInfoUpdate } from "@/types/users/userInfo.type";

export const getDetailOrCreateUserOptions = ({
    body,
    ...options
}: {
    body: UserInfoUpdate;
} & CustomUndefinedInitialDataOptions<UserInfoResponse>) => {
    return queryOptions({
        queryKey: ["get-detail-user-info"],
        queryFn: () => getDetailOrCreateUser({ body }),
        ...options,
    });
};
