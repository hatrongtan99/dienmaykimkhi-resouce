"use client";

import { getProductByCategorySlugOption } from "@/api/product/product.queryOption";
import Button from "@/components/custom/button/Button";
import CardMainProduct from "@/components/product/CardMainProduct";
import useLoadmore from "@/hook/useLoadmore";
import { ProductResponseWithPage } from "@/types/products/product.type";
import { buildParamsString } from "@/utils";
import { useParams, useSearchParams } from "next/navigation";
import { useState } from "react";

interface ListProductInPageProps {
    products: ProductResponseWithPage;
}

const ListProductInPage = ({
    products: initialData,
}: ListProductInPageProps) => {
    const params = useParams();
    const searchParams = useSearchParams();

    const lastCategorySlug =
        params.childCategorySlug[params.childCategorySlug.length - 1];
    const [page, setPage] = useState(0);
    const {
        listResult: listProduct,
        metadata,
        handleClickMore,
    } = useLoadmore(
        initialData,
        getProductByCategorySlugOption({
            categorySlug: lastCategorySlug,
            params: buildParamsString(
                { page, limit: 8 },
                searchParams.toString()
            ),
            staleTime: 1000 * 5 * 60,
        }),
        page,
        setPage
    );

    return (
        <div className="">
            <div className="grid grid-cols-4 gap-[2px]">
                {listProduct.map((product) => (
                    <CardMainProduct
                        productItem={product}
                        hasBrandImg
                        key={product.id}
                    />
                ))}
            </div>
            <div className="text-center my-4">
                {metadata.page + 1 < metadata.pageCount ? (
                    <Button
                        as="button"
                        onClick={handleClickMore}
                        variant="secondary"
                    >
                        Xem thêm {metadata.totalCount - listProduct.length} sản
                        phẩm
                    </Button>
                ) : null}
            </div>
        </div>
    );
};

export default ListProductInPage;
