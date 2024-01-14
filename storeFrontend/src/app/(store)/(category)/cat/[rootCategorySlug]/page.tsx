import BreadCrumb from "@/components/layout/BreadCrumb";
import ListCategoryLinkCard from "@/components/categories/ListCategoryLinkCard";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import { getChildCategoryByParentSlugOption } from "@/api/category/cactegory.queryOption";
import CategoryHeadRoot from "@/components/categories/CategoryHeadRoot";
import ListCategoryInPage from "./ListCategoryInPage";

const RootCategoryPage = async ({
    params: { rootCategorySlug },
}: {
    params: { rootCategorySlug: string };
}) => {
    const queryClient = new QueryClient();
    const listChildCate = await queryClient.fetchQuery(
        getChildCategoryByParentSlugOption({
            parentSlug: rootCategorySlug,
            params: "page=0&limit=1000",
        })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <div className="container">
                <BreadCrumb />

                <ListCategoryLinkCard categories={listChildCate.records} />

                <ListCategoryInPage initDataCategories={listChildCate} />
            </div>
        </HydrationBoundary>
    );
};

export default RootCategoryPage;
