"use client"

import { allBrands } from "@/data/brand"

import FilterSession from "@/components/filterProduct/FilterSession"
import BreadCrumb from "@/components/layout/BreadCrumb"
import ListCategoryLinkCard from "@/components/categories/ListCategoryLinkCard"
import CategoryHeadRoot from "@/components/categories/CategoryHeadRoot"
import ListCategoryScroll from "@/components/categories/ListCategoryScroll"
import { filterByPrice } from "@/data"

const RootCategoryPage = ({ params: { rootCategorySlug } }: { params: { rootCategorySlug: string } }) => {


    return (
        <div className="container">
            <BreadCrumb />
            <div className='grid grid-cols-1 sm:grid-cols-3 sm:gap-2 md:grid-cols-4 md:gap-4'>
                <aside className="col-span-1">
                    <div>
                        <ListCategoryScroll categories={[]} />
                    </div>
                    <div>
                        <FilterSession
                            title="CHỌN THEO HÃNG SẢN XUẤT"
                            dataFilter={allBrands.map((brand) => ({ id: brand.id, image: brand.thumbnail.url }))}
                            className="h-[300px] overflow-y-auto"
                        />
                    </div>
                    <div>
                        <FilterSession title="GIÁ BÁN" dataFilter={filterByPrice} />
                    </div>
                </aside>

                <div className="col-span-1 sm:col-span-2 md:col-span-3">

                    <div>
                        <ListCategoryLinkCard />
                    </div>

                    <div>
                        <CategoryHeadRoot title="máy khoan" />
                        <CategoryHeadRoot title="máy khoan" />
                        <CategoryHeadRoot title="máy khoan" />
                        <CategoryHeadRoot title="máy khoan" />
                        <CategoryHeadRoot title="máy khoan" />
                    </div>
                </div>
            </div>




        </div>
    )
}

export default RootCategoryPage