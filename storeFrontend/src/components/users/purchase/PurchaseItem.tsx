import { OrderItemResponse } from "@/types/orders/orders.type";
import { formatPriceDisplay } from "@/utils";
import Image from "next/image";
import Link from "next/link";

const PurchaseItem = ({ orderItem }: { orderItem: OrderItemResponse }) => {
    return (
        <div className="flex items-center justify-between border-b py-6 last:border-b-0 last:pb-0">
            <Link href={`/product/`}>
                <div className="flex">
                    <div className="border inline-block">
                        <Image
                            alt={orderItem.productName}
                            src={orderItem.thumbnailUrl}
                            width={100}
                            height={100}
                        />
                    </div>
                    <div className="grow ml-2">
                        <h4 className="text-base font-normal">
                            {orderItem.productName}
                        </h4>
                        <div className="text-xs font-normal text-text-light-color">
                            Số lượng: <span>{orderItem.quantity}</span>
                        </div>
                    </div>
                </div>
            </Link>

            <div className="min-w-[100px] text-sm">
                {orderItem.discount > 0 ? (
                    <span className="line-through text-stone-400 mr-2">
                        {formatPriceDisplay(orderItem.productPrice)}
                    </span>
                ) : null}
                <span className="text-red-500 font-medium">
                    {formatPriceDisplay(
                        orderItem.productPrice -
                            (orderItem.productPrice * orderItem.discount) / 100
                    )}
                </span>
            </div>
        </div>
    );
};

export default PurchaseItem;
