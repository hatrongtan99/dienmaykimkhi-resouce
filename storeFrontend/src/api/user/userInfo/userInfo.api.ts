import customFetch from "@/api";
import config from "@/constants/index";
import { UserInfoResponse, UserInfoUpdate } from "@/types/users/userInfo.type";

const BASE_URL_USER_API = config.BASE_URL_BFF + "/users/bff-customer/users";

export const getDetailOrCreateUser = ({ body }: { body: UserInfoUpdate }) => {
    return customFetch<UserInfoResponse>(BASE_URL_USER_API, {
        method: "POST",
        body: JSON.stringify(body),
    });
};

export const updateInfoUser = ({ body }: { body: UserInfoUpdate }) => {
    return customFetch<void>(BASE_URL_USER_API, {
        method: "POST",
        body: JSON.stringify(body),
    });
};
