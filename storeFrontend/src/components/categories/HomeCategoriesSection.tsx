import Link from "next/link";
import React from "react";
import CardMainProduct from "../product/CardMainProduct";
import { CategoryResponse } from "@/types/products/categories.type";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import { getAllChildCategoryByParentIdOption } from "@/api/category/cactegory.queryOption";
import { getProductByCategorySlugOption } from "@/api/product/product.queryOption";
import { getDetailPromotionProductOptopns } from "@/api/promotions/product/product.queryOptions";

interface HomeCategoriesSectionProps {
    category: CategoryResponse;
}

const HomeCategoriesSection = async ({
    category,
}: HomeCategoriesSectionProps) => {
    const queryClient = new QueryClient();
    const categoryChild = await queryClient.fetchQuery(
        getAllChildCategoryByParentIdOption({
            id: category.id,
            params: "page=0&limit=4",
        })
    );

    const productByCategorySlug = await queryClient.fetchQuery(
        getProductByCategorySlugOption({
            categorySlug: category.slug,
            params: "page=0&limit=12",
        })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <section className="container mt-4">
                <div className="flex border-b-2 border-primary-color bg-white">
                    <Link
                        href={`/cat/${category.slug}`}
                        className="bg-primary-color rounded-sm"
                    >
                        <h3 className="text-white font-semibold uppercase px-4 py-2">
                            {category.name}
                        </h3>
                    </Link>

                    {categoryChild.records.map((child) => (
                        <Link
                            href={`cat/${category.slug}/${child.slug}`}
                            key={child.id}
                        >
                            <h3 className="px-4 py-2 capitalize text-sm text-secondary-color">
                                {child.name}
                            </h3>
                        </Link>
                    ))}

                    <Link
                        href={`/cat/${category.slug}`}
                        className="flex flex-grow"
                    >
                        <h3 className="px-4 py-2 capitalize text-sm text-secondary-color ml-auto">
                            danh mục khác
                        </h3>
                    </Link>
                </div>
                {/* list product */}
                <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 container gap-[2px]">
                    {productByCategorySlug.records.map((product) => {
                        return (
                            <CardMainProduct
                                productItem={product}
                                key={product.id}
                            />
                        );
                    })}
                </div>
            </section>
        </HydrationBoundary>
    );
};

export default HomeCategoriesSection;
