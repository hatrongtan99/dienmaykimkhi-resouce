import { ProductItemResponse } from "@/types/product.type";
import Image from "next/image";
import Link from "next/link";

interface CardMainProduct {
    hasBrandImg?: boolean;
    productItem: ProductItemResponse;
}

const CardMainProduct = ({ hasBrandImg, productItem }: CardMainProduct) => {
    return (
        <div className="rounded-sm bg-white h-full overflow-hidden group hover:border hover:border-red-300 shadow-lg">
            <div className="px-4 py-3">
                <Link
                    href={`/product/${productItem.slug}`}
                    className="block text-center"
                >
                    <Image
                        alt={productItem.name}
                        src={productItem.thumbnail}
                        width={200}
                        height={200}
                        className="group-hover:scale-105 transition-all duration-150 inline-block"
                    />
                    <h6 className="text-sm font-medium tracking-wide leading-6 mt-2 line-clamp-2 min-h-[50px] text-left">
                        {productItem.name}
                    </h6>
                </Link>

                {hasBrandImg ? (
                    <Link
                        href={`/brand/${productItem.brand.slug}`}
                        className="border border-gray-200 rounded-sm inline-block"
                    >
                        <Image
                            alt={productItem.brand.name}
                            src={productItem.brand.urlThumbnail || ""}
                            height={30}
                            width={60}
                            className="py-0.5 px-1"
                        />
                    </Link>
                ) : null}
                <div className="">
                    <strong className="text-red-600 font-bold text-sm md:text-base line-clamp-1">
                        {productItem.price.toLocaleString()} đ
                    </strong>
                    {true ? <div></div> : null}
                </div>
            </div>
        </div>
    );
};

export default CardMainProduct;
