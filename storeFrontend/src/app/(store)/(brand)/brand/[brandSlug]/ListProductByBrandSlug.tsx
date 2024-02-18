"use client";

import { getProductByBrandSlugOption } from "@/api/product/product.queryOption";
import Button from "@/components/custom/button/Button";
import CardMainProduct from "@/components/product/CardMainProduct";
import useLoadmore from "@/hook/useLoadmore";
import {
    ProductItemResponse,
    ProductResponseWithPage,
} from "@/types/products/product.type";
import { buildParamsString } from "@/utils";
import { useSearchParams } from "next/navigation";
import { useState } from "react";

interface ListProductByBrandSlugProps {
    slug: string;
    initialData: ProductResponseWithPage;
}

const ListProductByBrandSlug = ({
    slug,
    initialData,
}: ListProductByBrandSlugProps) => {
    const searchParams = useSearchParams();
    const [page, setPage] = useState(0);

    const {
        listResult: productBySlugBrand,
        handleClickMore,
        metadata,
    } = useLoadmore(
        initialData,
        getProductByBrandSlugOption({
            brandSlug: slug,
            params: buildParamsString(
                {
                    page,
                    limit: 5,
                },
                searchParams.toString()
            ),
            staleTime: 1000 * 60 * 5,
        }),
        page,
        setPage
    );

    return (
        <>
            <div className="grid grid-cols-5 gap-[2px]">
                {productBySlugBrand.map((product) => (
                    <CardMainProduct
                        productItem={product}
                        hasBrandImg={true}
                        key={product.id}
                    />
                ))}
            </div>

            <div className="mt-4 text-center">
                <Button
                    variant="secondary"
                    as="button"
                    onClick={handleClickMore}
                >
                    Xem thêm {metadata.totalCount - productBySlugBrand.length}{" "}
                    sản phẩm
                </Button>
            </div>
        </>
    );
};

export default ListProductByBrandSlug;
