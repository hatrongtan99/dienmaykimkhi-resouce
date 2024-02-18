"use client";

import Link from "next/link";
import React from "react";
import SlideWraper from "../common/SlideWraper";
import CardMainProduct from "../product/CardMainProduct";
import { CategoryResponse } from "@/types/products/categories.type";
import { useQuery } from "@tanstack/react-query";
import { getProductByCategorySlugOption } from "@/api/product/product.queryOption";
import { useParams } from "next/navigation";

interface CategoryHeadRootProps {
    category: CategoryResponse;
}

const CategoryHeadRoot = ({ category }: CategoryHeadRootProps) => {
    const params = useParams();

    const { data, isSuccess } = useQuery(
        getProductByCategorySlugOption({
            categorySlug: category.slug,
            params: "page=0&limit=8",
        })
    );

    return (
        <section className="mb-4">
            <div className="bg-white py-2 px-3">
                <Link
                    href={`/cat/${params.rootCategorySlug}/${category.slug}`}
                    className="rounded-sm uppercase text-xl font-semibold pl-3 border-l-[3px] border-l-red-600 text-secondary-color hover:text-secondary-light-color"
                >
                    {category.name}
                </Link>
            </div>

            <div className="mt-0.5">
                {isSuccess && data.records.length > 0 ? (
                    <SlideWraper
                        handleMoveSlide={() => {}}
                        handleTransionEnd={() => {}}
                        style={{
                            display: "flex",
                        }}
                    >
                        {data.records.map((product) => (
                            <div
                                className="w-[calc(100%/4)] flex-shrink-0"
                                key={product.id}
                            >
                                <CardMainProduct
                                    productItem={product}
                                    hasBrandImg
                                />
                            </div>
                        ))}
                    </SlideWraper>
                ) : null}
            </div>
        </section>
    );
};

export default CategoryHeadRoot;
