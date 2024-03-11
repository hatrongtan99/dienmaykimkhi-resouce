import customFetch from "@/api";
import config from "@/constants/index";
import {
    AddressSaveOrUpdateDto,
    AddressUserResponse,
} from "@/types/users/addressUser.type";

const BASE_URL_USER_API =
    config.BASE_URL_BFF + "/users/bff-customer/users/address";

// address user

export const getListAddressUser = () => {
    return customFetch<AddressUserResponse[]>(BASE_URL_USER_API);
};

export const getDetailAddressById = ({ addressId }: { addressId: number }) => {
    return customFetch<AddressUserResponse>(
        BASE_URL_USER_API + `/${addressId}`
    );
};

export const createNewAddressUser = ({
    body,
}: {
    body: AddressSaveOrUpdateDto;
}) => {
    return customFetch<void>(BASE_URL_USER_API, {
        method: "POST",
        body: JSON.stringify(body),
    });
};

export const updateAddress = ({
    addressId,
    body,
}: {
    addressId: number;
    body: AddressSaveOrUpdateDto;
}) => {
    return customFetch<void>(BASE_URL_USER_API + `/${addressId}`, {
        method: "PUT",
        body: JSON.stringify(body),
    });
};

export const updateDefaultAddress = ({ addressId }: { addressId: number }) => {
    return customFetch<void>(BASE_URL_USER_API + `/${addressId}`, {
        method: "PATCH",
    });
};

export const deleteAddress = ({ addressId }: { addressId: number }) => {
    return customFetch<void>(BASE_URL_USER_API + `/${addressId}`, {
        method: "DELETE",
    });
};

export const getAddressDefault = () => {
    return customFetch<AddressUserResponse>(BASE_URL_USER_API + "/default");
};
