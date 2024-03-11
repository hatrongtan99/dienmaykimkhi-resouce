"use client";
import { IoLocationSharp } from "react-icons/io5";
import Button from "../custom/button/Button";
import { useRouter } from "next/navigation";
import { AddressUserResponse } from "@/types/users/addressUser.type";
import { useContext } from "react";
import { CheckoutContext } from "@/context/checkout/CheckoutContextProvider";

const AddressCheckout = ({ address }: { address?: AddressUserResponse }) => {
    const router = useRouter();
    const { addressCheckout } = useContext(CheckoutContext);

    if (addressCheckout) {
        return (
            <div className="my-3 p-6 bg-white rounded-sm">
                <div className="flex items-center text-primary-color font-medium mb-2">
                    <IoLocationSharp />
                    <span className="ml-2">Địa Chỉ Nhận Hàng</span>
                </div>
                <div className="">
                    <span className="text-base font-semibold">
                        {addressCheckout.fullName} (+84){" "}
                        {addressCheckout.phoneNumber}
                    </span>
                    <span className="ml-6 font-normal text-base">
                        {[
                            addressCheckout.addressLine1,
                            addressCheckout.addressLine2,
                            addressCheckout.addressLine3,
                        ].join(", ")}
                    </span>
                    {addressCheckout.isDefault ? (
                        <span className="inline-block px-1 py-0.5 text-[10px] border border-primary-color text-primary-color rounded-sm mx-4">
                            Mặc Định
                        </span>
                    ) : null}
                    <Button variant="text" className="text-secondary-color">
                        Thay Đổi
                    </Button>
                </div>
            </div>
        );
    } else {
    }
};

export default AddressCheckout;
