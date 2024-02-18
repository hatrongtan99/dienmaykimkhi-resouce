import React from "react";
import PurchaseItem from "./PurchaseItem";
import { formatPriceDisplay } from "@/utils";
import Button from "@/components/custom/button/Button";
import { MdOutlineLocalShipping } from "react-icons/md";

const PurchaseContainer = () => {
    return (
        <div className="my-4">
            <div className="rounded-b-md overflow-hidden bg-white px-8 py-8">
                <div className="border-b pb-2 text-end">
                    <div className="inline-flex items-center">
                        <div className="flex items-center text-teal-600">
                            <MdOutlineLocalShipping className="mx-2" />
                            <span className="text-sm">
                                Đơn hàng đã được giao thành công
                            </span>
                        </div>
                        <span className="mx-2 inline-block border-l h-[24px]"></span>
                        <span className="uppercase text-base font-medium text-primary-color">
                            HOÀN THÀNH
                        </span>
                    </div>
                </div>
                <div className="mt-2">
                    <PurchaseItem />
                    <PurchaseItem />
                    <PurchaseItem />
                    <PurchaseItem />
                    <PurchaseItem />
                    <PurchaseItem />
                </div>
            </div>
            <div className="rounded-t-md text-end mt-0.5 bg-white px-6 py-8 relative">
                <div className="inline-flex items-center ">
                    <span className="mr-4 text-sm font-normal">
                        Thành tiền:
                    </span>
                    <span className="text-xl text-red-500 font-medium">
                        {formatPriceDisplay(299000)}
                    </span>
                </div>
                <div className="absolute top-1/2 -translate-y-1/2 left-1/2 -translate-x-1/2">
                    <Button size="md" as="button">
                        Đánh giá
                    </Button>
                </div>
            </div>
        </div>
    );
};

export default PurchaseContainer;
