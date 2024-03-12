import { getChildCategoryByParentSlugOption } from "@/api/category/cactegory.queryOption";
import { getProductByCategorySlugOption } from "@/api/product/product.queryOption";
import SortProductSession from "@/components/filterProduct/SortProductSession";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import ListProductInPage from "./ListProductInPage";
import ListCategoryLinkCard from "@/components/categories/ListCategoryLinkCard";
import { buildParamsString } from "@/utils";
import CardMainProduct from "@/components/product/CardMainProduct";

export interface ParamsChildCategories {
    rootCategorySlug: string;
    childCategorySlug: string[];
}

const ChildCategoryPage = async ({
    params: { rootCategorySlug, childCategorySlug },
    searchParams,
}: {
    params: ParamsChildCategories;
    searchParams: {};
}) => {
    const queryClient = new QueryClient();
    const lastChildCategorySlug =
        childCategorySlug[childCategorySlug.length - 1];

    const listChildOfCurrent = await queryClient.fetchQuery(
        getChildCategoryByParentSlugOption({
            parentSlug: childCategorySlug[childCategorySlug.length - 1],
            params: "page=0&limit=1000",
        })
    );

    const listProductByChildCateSlug = await queryClient.fetchQuery(
        getProductByCategorySlugOption({
            categorySlug: lastChildCategorySlug,
            params: buildParamsString({ ...searchParams, page: 0, limit: 8 }),
        })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            {listChildOfCurrent.records.length ? (
                <ListCategoryLinkCard categories={listChildOfCurrent.records} />
            ) : null}
            <SortProductSession />
            {/* list product */}
            <ListProductInPage products={listProductByChildCateSlug} />
        </HydrationBoundary>
    );
};

export default ChildCategoryPage;
