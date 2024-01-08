import ListCategoryScroll from '@/components/categories/ListCategoryScroll'
import Button from '@/components/custom/button/Button'
import FilterSession from '@/components/filterProduct/FilterSession'
import SortProductSession from '@/components/filterProduct/SortProductSession'
import BreadCrumb from '@/components/layout/BreadCrumb'
import CardMainProduct from '@/components/product/CardMainProduct'
import { filterByPrice } from '@/data'
import { allBrands } from '@/data/brand'
import React from 'react'

const ChildCategoryPage = () => {
    return (
        <div className='container'>

            <BreadCrumb />

            <div className='grid grid-cols-1 sm:grid-cols-3 sm:gap-2 md:grid-cols-4 md:gap-4'>
                <aside className="col-span-1">
                    <div>
                        <ListCategoryScroll categories={[]} />
                    </div>
                    <div>
                        <FilterSession title="CHỌN THEO HÃNG SẢN XUẤT" dataFilter={allBrands.map((brand) => ({ id: brand.id, image: brand.thumbnail.url, label: brand.name }))} />
                    </div>
                    <div>
                        <FilterSession title="GIÁ BÁN" dataFilter={filterByPrice} />
                    </div>
                </aside>
                <div className='col-span-1 sm:col-span-2 md:col-span-3'>
                    <div>
                        <SortProductSession />
                    </div>
                    <div className='grid grid-cols-4 gap-[2px]'>
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                        <CardMainProduct productItem={""} />
                    </div>
                    <div className='my-4 text-center'>
                        <Button className="px-8" size='sm'>
                            Xem thêm 892 máy khoan
                        </Button>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default ChildCategoryPage