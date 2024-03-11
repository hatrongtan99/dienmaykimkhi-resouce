import { updateDefaultAddress } from "@/api/user/address/addressUser.api";
import { getListAddressUserOptions } from "@/api/user/address/addressUser.queryOptions";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { ReactNode, createContext } from "react";

export const AddressContext = createContext(
    {} as {
        validateAddressList: () => void;
        hanleClickChangeDefaultAddress: (addressId: number) => Promise<void>;
    }
);

const AddressContextProvider = ({ children }: { children: ReactNode }) => {
    const queryClient = useQueryClient();

    const validateAddressList = () => {
        queryClient.invalidateQueries({
            queryKey: getListAddressUserOptions()["queryKey"],
        });
    };

    const { mutateAsync: mutateAsyncUpdateDefaultAddress } = useMutation({
        mutationFn: (addressId: number) => updateDefaultAddress({ addressId }),
        onSuccess: () => {
            validateAddressList();
        },
    });

    const hanleClickChangeDefaultAddress = async (addressId: number) => {
        try {
            await mutateAsyncUpdateDefaultAddress(addressId);
        } catch (error) {
            console.log(error);
        }
    };
    return (
        <AddressContext.Provider
            value={{ validateAddressList, hanleClickChangeDefaultAddress }}
        >
            {children}
        </AddressContext.Provider>
    );
};

export default AddressContextProvider;
