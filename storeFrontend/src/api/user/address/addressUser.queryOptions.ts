import { queryOptions } from "@tanstack/react-query";
import {
    getAddressDefault,
    getDetailAddressById,
    getListAddressUser,
} from "./addressUser.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { AddressUserResponse } from "@/types/users/addressUser.type";

export const getListAddressUserOptions = ({
    ...options
}: CustomUndefinedInitialDataOptions<AddressUserResponse[]> = {}) => {
    return queryOptions({
        queryKey: ["list-address-user"],
        queryFn: getListAddressUser,
        ...options,
    });
};

export const getDetailAddressByIdOptions = ({
    addressId,
    ...options
}: {
    addressId: number;
} & CustomUndefinedInitialDataOptions<AddressUserResponse>) => {
    return queryOptions({
        queryKey: ["address-user-by-id", { addressId }],
        queryFn: () => getDetailAddressById({ addressId }),
        ...options,
    });
};

export const getDetailAddressDefaultOptions = ({
    ...options
}: CustomUndefinedInitialDataOptions<AddressUserResponse> = {}) => {
    return queryOptions({
        queryKey: ["address-user-default"],
        queryFn: getAddressDefault,
        ...options,
    });
};
