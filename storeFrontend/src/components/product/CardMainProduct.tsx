import { getDetailPromotionProductOptopns } from "@/api/promotions/product/product.queryOptions";
import { ProductItemResponse } from "@/types/products/product.type";
import { PromotionProductResponse } from "@/types/promotions/promotionProduct.type";
import { formatPriceDisplay } from "@/utils";
import { QueryClient } from "@tanstack/react-query";
import Image from "next/image";
import Link from "next/link";

interface CardMainProduct {
    hasBrandImg?: boolean;
    productItem: ProductItemResponse;
}

const CardMainProduct = async ({
    hasBrandImg,
    productItem,
}: CardMainProduct) => {
    const queryClient = new QueryClient();

    const { brand, id, name, price, slug, thumbnail } = productItem;

    const promotionProduct = await queryClient.fetchQuery(
        getDetailPromotionProductOptopns({ productId: id })
    );

    const promotionPrice =
        promotionProduct && promotionProduct.percentDiscount > 0
            ? (promotionProduct.percentDiscount * price) / 100
            : 0;

    return (
        <div className="rounded-sm bg-white h-full overflow-hidden group hover:border hover:border-red-300 shadow-lg">
            <div className="px-4 py-3 relative">
                {/* image + name */}
                <Link href={`/product/${slug}`} className="block text-center">
                    <Image
                        alt={name}
                        src={thumbnail}
                        width={200}
                        height={200}
                        className="group-hover:scale-105 transition-all duration-150 inline-block"
                    />
                    <h6 className="text-sm font-medium tracking-wide leading-6 mt-2 line-clamp-2 min-h-[50px] text-left">
                        {name}
                    </h6>
                </Link>
                {/* brand image */}
                {hasBrandImg ? (
                    <Link
                        href={`/brand/${brand.slug}`}
                        className="border border-gray-200 rounded-sm inline-block"
                    >
                        <Image
                            alt={brand.name}
                            src={brand.urlThumbnail || ""}
                            height={30}
                            width={60}
                            className="py-0.5 px-1"
                        />
                    </Link>
                ) : null}
                {/* price */}
                <div className="">
                    <strong className="text-red-600 font-bold text-sm md:text-base line-clamp-1">
                        {formatPriceDisplay(price - promotionPrice)}
                    </strong>
                </div>
                {promotionPrice !== 0 ? (
                    <div className="absolute top-2 right-2 px-2 py-0.5 rounded-r-md bg-yellow-400 before:border-[14px] before:border-r-yellow-400 before:border-transparent before:contents-[''] before:absolute before:top-0 before:right-[100%]">
                        <span className="text-sm text-white ">
                            -{promotionProduct!.percentDiscount}%
                        </span>
                    </div>
                ) : null}
            </div>
        </div>
    );
};

export default CardMainProduct;
