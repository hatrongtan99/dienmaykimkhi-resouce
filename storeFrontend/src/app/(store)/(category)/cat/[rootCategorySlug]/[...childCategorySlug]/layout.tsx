import { ReactNode } from "react";
import { ParamsChildCategories } from "./page";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import { getChildCategoryByParentSlugOption } from "@/api/category/cactegory.queryOption";
import BreadCrumb from "@/components/layout/BreadCrumb";
import ListCategoryLinkScroll from "@/components/categories/ListCategoryLinkScroll";
import FilterSession from "@/components/filterProduct/FilterSession";
import { getAllBrandOption } from "@/api/brand/brand.queryOption";
import { filterByPrice } from "@/data";

// export async function generateStaticParams({
//     params: { rootCategorySlug },
// }: {
//     params: { rootCategorySlug: string };
// }) {
//     // const listCategory
//     return [];
// }

const ChildCategoryLayout = async ({
    children,
    params: { rootCategorySlug, childCategorySlug },
}: {
    children: ReactNode;
    params: ParamsChildCategories;
}) => {
    const queryClient = new QueryClient();

    const listChildCategoryOfParent = await queryClient.fetchQuery(
        getChildCategoryByParentSlugOption({
            parentSlug:
                childCategorySlug[childCategorySlug.length - 2] ||
                rootCategorySlug,
            params: "page=0&limit=1000",
        })
    );

    const allBrands = await queryClient.fetchQuery(getAllBrandOption());
    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <div className="container">
                <BreadCrumb />

                <div className="grid grid-cols-1 sm:grid-cols-3 sm:gap-2 md:grid-cols-4 md:gap-4">
                    <aside className="col-span-1">
                        <ListCategoryLinkScroll
                            categories={listChildCategoryOfParent.records}
                        />

                        <FilterSession
                            title="CHỌN THEO HÃNG SẢN XUẤT"
                            dataFilter={allBrands.map((brand) => ({
                                id: brand.id,
                                image: brand.thumbnail.url,
                                label: brand.name,
                                dataSet: brand.id.toString(),
                            }))}
                            fieldFilter="brand"
                        />

                        <FilterSession
                            title="GIÁ BÁN"
                            dataFilter={filterByPrice.map((i) => ({
                                ...i,
                                dataSet: i.value,
                            }))}
                            fieldFilter="price"
                        />
                    </aside>

                    {children}
                </div>
            </div>
        </HydrationBoundary>
    );
};

export default ChildCategoryLayout;
