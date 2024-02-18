import config from "@/constants/index";
import customFetch from "..";
import { UserAuthenticate } from "@/types/users/userInfo.type";

export const getCurrentUser = async ({ ...options }: {} & RequestInit = {}) => {
    return customFetch<UserAuthenticate>(
        config.BASE_URL_BFF + "/authenticate",
        { ...options }
    );
};
