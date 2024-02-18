import Button from "@/components/custom/button/Button";
import { AddressUserResponse } from "@/types/users/addressUser.type";
import React from "react";

const AddressItem = ({ address }: { address: AddressUserResponse }) => {
    return (
        <div className="py-6 border-b last:border-b-0">
            <div className="flex justify-between">
                <div className="flex items-center">
                    <h4 className="capitalize text-base font-semibold">
                        {address.fullName}
                    </h4>
                    <span className="inline-block border-l border-l-stone-400 h-[20px] mx-2" />
                    <span className="text-sm text-text-light-color">
                        {address.phoneNumber}
                    </span>
                </div>
                <Button
                    as="button"
                    variant="text"
                    className="text-sm text-secondary-color"
                >
                    Cập nhật
                </Button>
            </div>

            <div className="flex justify-between">
                <div className="text-sm text-text-light-color">
                    <p className="line-clamp-1">{address.addressLine1}</p>
                    <p className="line-clamp-1">
                        {[address.addressLine1, address.addressLine2].join(
                            ", "
                        )}
                    </p>
                </div>
                <Button
                    as="button"
                    variant="secondary-border"
                    size="sm"
                    className="h-[30px] min-h-0"
                    disable={address.isDefault}
                >
                    Thiết lập mặc định
                </Button>
            </div>
            {address.isDefault ? (
                <div className="mt-2 flex space-x-3">
                    <span className="inline-block px-2 py-1 border-primary-color text-primary-color text-xs border rounded-sm">
                        Mặc định
                    </span>
                    <span className="inline-block px-2 py-1 text-text-light-color text-xs border rounded-sm">
                        Địa chỉ lấy hàng
                    </span>
                    <span className="inline-block px-2 py-1 text-text-light-color text-xs border rounded-sm">
                        Địa chỉ trả hàng
                    </span>
                </div>
            ) : null}
        </div>
    );
};

export default AddressItem;
