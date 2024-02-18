import { formatPriceDisplay } from "@/utils";
import Image from "next/image";
import Link from "next/link";
import React from "react";

const PurchaseItem = () => {
    return (
        <div className="flex items-center justify-between border-b py-6 last:border-b-0 last:pb-0">
            <Link href={""}>
                <div className="flex">
                    <div className="border inline-block">
                        <Image alt="" src="" width={100} height={100} />
                    </div>
                    <div className="grow ml-2">
                        <h4 className="text-base font-normal">
                            Kem dưỡng hỗ trợ làm dịu & phục hồi da La
                            Roche-Posay Cicaplast Baume B5+ 100ml
                        </h4>
                        <div className="text-xs font-normal text-text-light-color">
                            Số lượng: <span>1</span>
                        </div>
                    </div>
                </div>
            </Link>

            <div className="min-w-[100px] text-sm">
                <span className="line-through text-stone-400 mr-2">
                    {formatPriceDisplay(299000)}
                </span>
                <span className="text-red-500 font-medium">
                    {formatPriceDisplay(299000)}
                </span>
            </div>
        </div>
    );
};

export default PurchaseItem;
