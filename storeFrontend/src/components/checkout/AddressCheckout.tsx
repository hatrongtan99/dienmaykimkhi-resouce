import React from "react";
import { IoLocationSharp } from "react-icons/io5";
import Button from "../custom/button/Button";

const AddressCheckout = () => {
    return (
        <div className="my-3 p-6 bg-white rounded-sm">
            <div className="flex items-center text-primary-color font-medium mb-2">
                <IoLocationSharp />
                <span className="ml-2">Địa Chỉ Nhận Hàng</span>
            </div>
            <div className="">
                <span className="text-base font-semibold">
                    Hà Trọng Tấn (+84) 346997126
                </span>
                <span className="ml-6 font-normal text-base">
                    86/5 bàu cát 2 tân bình, Phường 12, Quận Tân Bình, TP. Hồ
                    Chí Minh
                </span>
                <span className="inline-block px-1 py-0.5 text-[10px] border border-primary-color text-primary-color rounded-sm mx-4">
                    Mặc Định
                </span>
                <Button variant="text" className="text-secondary-color">
                    Thay Đổi
                </Button>
            </div>
        </div>
    );
};

export default AddressCheckout;
