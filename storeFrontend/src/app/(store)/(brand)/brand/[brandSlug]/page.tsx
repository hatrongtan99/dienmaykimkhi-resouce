import SortProductSession from "@/components/filterProduct/SortProductSession";
import BreadCrumb from "@/components/layout/BreadCrumb";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import ListProductByBrandSlug from "./ListProductByBrandSlug";
import { getProductByBrandSlugOption } from "@/api/product/product.queryOption";
import { buildParamsString } from "@/utils";

const BrandPage = async ({
    params: { brandSlug },
    searchParams,
}: {
    params: { brandSlug: string };
    searchParams: {};
}) => {
    const queryClient = new QueryClient();

    const listProductByBrandSlug = await queryClient.fetchQuery(
        getProductByBrandSlugOption({
            brandSlug,
            params: buildParamsString({ ...searchParams, page: 0, limit: 5 }),
        })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <div className="container">
                <BreadCrumb />
                <SortProductSession />
                <section className="bg-white p-4">
                    <ListProductByBrandSlug
                        slug={brandSlug}
                        initialData={listProductByBrandSlug}
                    />
                </section>
            </div>
        </HydrationBoundary>
    );
};

export default BrandPage;
