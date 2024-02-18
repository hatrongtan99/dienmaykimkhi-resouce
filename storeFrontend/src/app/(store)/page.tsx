import { getAllBrandOption } from "@/api/brand/brand.queryOption";
import { getListParentCategoryOption } from "@/api/category/cactegory.queryOption";
import SlideBrand from "@/components/brands/SlideBrand";
import HomeCategoriesSection from "@/components/categories/HomeCategoriesSection";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import HomeSlideImg from "./HomeSlideImg";

export default async function Home() {
    const queryClient = new QueryClient();

    const brands = await queryClient.fetchQuery(getAllBrandOption());

    const allParentCategories = await queryClient.fetchQuery(
        getListParentCategoryOption({ params: "page=0&limit=1000" })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <>
                <section className="container">
                    <div className="ml-56 mt-2 h-[340px] grid grid-cols-3 ">
                        <div className="ml-2 col-span-2">
                            <HomeSlideImg />
                        </div>
                        <div className="col-span-1">post new</div>
                    </div>
                </section>
                <SlideBrand brands={brands} />
                {allParentCategories.records.map((category) => (
                    <HomeCategoriesSection
                        category={category}
                        key={category.id}
                    />
                ))}
            </>
        </HydrationBoundary>
    );
}
