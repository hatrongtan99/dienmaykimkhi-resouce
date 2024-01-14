import { getAllBrandOption } from "@/api/brand/brand.queryOption";
import { getChildCategoryByParentSlugOption } from "@/api/category/cactegory.queryOption";
import { getProductByCategorySlugOption } from "@/api/product/product.queryOption";
import ListCategoryLinkScroll from "@/components/categories/ListCategoryLinkScroll";
import FilterSession from "@/components/filterProduct/FilterSession";
import SortProductSession from "@/components/filterProduct/SortProductSession";
import BreadCrumb from "@/components/layout/BreadCrumb";
import { filterByPrice } from "@/data";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import ListProductInPage from "./ListProductInPage";
import ListCategoryLinkCard from "@/components/categories/ListCategoryLinkCard";
import { buildParamsString } from "@/utils";

const ChildCategoryPage = async ({
    params: { rootCategorySlug, childCategorySlug },
    searchParams,
}: {
    params: { rootCategorySlug: string; childCategorySlug: string[] };
    searchParams: {};
}) => {
    const queryClient = new QueryClient();
    const lastChildCategorySlug =
        childCategorySlug[childCategorySlug.length - 1];

    const listChildCategoryOfParent = await queryClient.fetchQuery(
        getChildCategoryByParentSlugOption({
            parentSlug:
                childCategorySlug[childCategorySlug.length - 2] ||
                rootCategorySlug,
            params: "page=0&limit=1000",
        })
    );

    const listChildOfCurrent = await queryClient.fetchQuery(
        getChildCategoryByParentSlugOption({
            parentSlug: childCategorySlug[childCategorySlug.length - 1],
            params: "page=0&limit=1000",
        })
    );

    const allBrands = await queryClient.fetchQuery(getAllBrandOption());

    const listProductByChildCateSlug = await queryClient.fetchQuery(
        getProductByCategorySlugOption({
            categorySlug: lastChildCategorySlug,
            params: buildParamsString({ ...searchParams, page: 0, limit: 8 }),
        })
    );
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

                    <div className="col-span-1 sm:col-span-2 md:col-span-3">
                        {listChildOfCurrent.records.length ? (
                            <ListCategoryLinkCard
                                categories={listChildOfCurrent.records}
                            />
                        ) : null}
                        <SortProductSession />
                        {/* list product */}
                        <ListProductInPage
                            products={listProductByChildCateSlug}
                        />
                    </div>
                </div>
            </div>
        </HydrationBoundary>
    );
};

export default ChildCategoryPage;
