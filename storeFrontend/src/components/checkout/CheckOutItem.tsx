import { formatPriceDisplay } from "@/utils";
import Image from "next/image";
import Link from "next/link";
import React from "react";

const CheckOutItem = () => {
    return (
        <div className="flex my-1 bg-white px-6 py-6 items-center">
            <div className="w-[50%] lg:w-[40%]">
                <Link href={`/product`} className="flex items-center">
                    <div className="w-[60px] h-[60px] relative flex-shrink-0">
                        <Image src={""} fill alt={""} />
                    </div>
                    <div className="ml-4 text-sm font-normal">
                        <h3 className="line-clamp-1">
                            Sạc Pin 3S 12.6V 12V 1A 2A VITO Cho Máy Khoan Pin
                        </h3>
                    </div>
                </Link>
            </div>
            <div className="lg:flex-[2] w-6"></div>
            <div className="flex-1 text-center text-sm font-medium">
                {formatPriceDisplay(22000)}
            </div>
            <div className="flex-1 text-center text-sm font-medium">1</div>
            <div className="flex-[2] text-right text-sm font-semibold">
                {formatPriceDisplay(22000)}
            </div>
        </div>
    );
};

export default CheckOutItem;
