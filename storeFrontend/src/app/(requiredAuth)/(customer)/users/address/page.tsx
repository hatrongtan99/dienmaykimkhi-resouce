"use client";
import { getListAddressUserOptions } from "@/api/user/address/addressUser.queryOptions";
import ModalPopup from "@/components/common/ModalPopup";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import Button from "@/components/custom/button/Button";
import AddressItem from "@/components/users/address/AddressItem";
import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { IoMdAdd } from "react-icons/io";

const AddressPageUser = () => {
    const {
        data: listAddress,
        isLoading,
        isError,
    } = useQuery(
        getListAddressUserOptions({
            staleTime: 1000 * 60 * 5,
            refetchOnWindowFocus: false,
        })
    );

    const [isOpenForm, setIsOpenForm] = useState(false);

    if (isError) {
        throw new Error("some thing wrong!");
    }
    if (isLoading) {
        return (
            <div className="w-full min-h-[400px] bg-white flex items-center justify-center rounded-md">
                <LoadingBubble />
            </div>
        );
    }
    if (listAddress)
        return (
            <div className="py-6 px-8 bg-white w-full min-h-[400px] rounded-md">
                <div className="pb-3 border-b">
                    <div className="flex justify-between">
                        <h3 className="text-lg font-semibold">
                            Địa chỉ của tôi
                        </h3>
                        <Button
                            as="button"
                            leftIcon={<IoMdAdd size={20} />}
                            className="text-sm font-light"
                            onClick={() => setIsOpenForm(true)}
                        >
                            Thêm địa chỉ mới
                        </Button>
                    </div>
                </div>
                <div className="">
                    {listAddress.map((address) => (
                        <AddressItem address={address} key={address.id} />
                    ))}
                </div>

                {/* create new address form */}
                {isOpenForm ? (
                    <ModalPopup handleClick={() => setIsOpenForm(false)}>
                        <div className="bg-white"> heloo</div>
                    </ModalPopup>
                ) : null}
            </div>
        );
};

export default AddressPageUser;
