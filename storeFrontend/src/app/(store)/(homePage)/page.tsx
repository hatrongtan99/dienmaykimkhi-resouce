import { getAllBrandOption } from "@/api/brand/brand.queryOption";
import { getListParentCategoryOption } from "@/api/category/cactegory.queryOption";
import SlideBrand from "@/components/brands/SlideBrand";
import HomeCategoriesSection from "@/components/categories/HomeCategoriesSection";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";

export default async function Home() {
    const queryClient = new QueryClient();

    const brands = await queryClient.fetchQuery(getAllBrandOption());

    const allParentCategories = await queryClient.fetchQuery(
        getListParentCategoryOption({ params: "page=0&limit=1000" })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <SlideBrand brands={brands} />
            {allParentCategories.records.map((category) => (
                <HomeCategoriesSection category={category} key={category.id} />
            ))}
        </HydrationBoundary>
    );
}
