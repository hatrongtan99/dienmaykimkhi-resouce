import BreadCrumb from '@/components/layout/BreadCrumb'
import CatalogSectionDetail from '@/components/product/detailPage/CatalogSectionDetail'
import NameSectionDetail from '@/components/product/detailPage/NameSectionDetail'
import PriceDetailProduct from '@/components/product/detailPage/PriceDetailProduct'
import ProductDescription from '@/components/product/detailPage/ProductDescription'
import RatingBox from '@/components/product/detailPage/RatingBox'
import SlideDetailProduct from '@/components/product/detailPage/SlideDetailProduct'


const imagesUrl = [
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-1-1639707151.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-3-1639707174.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-8-1639707518.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-1639706758.jpeg",

]



const ProductDetailPage = () => {
    return (
        <section className='container'>
            <BreadCrumb />
            <div className='bg-white p-4'>
                <div className='grid grid-cols-5 gap-8'>
                    <div className='col-span-2'>
                        <SlideDetailProduct images={imagesUrl} />
                    </div>

                    <div className='col-span-3'>
                        <div>
                            <NameSectionDetail name='Máy khoan và vặn vít Makita DF0300 (10MM)' />
                            <PriceDetailProduct />
                        </div>
                    </div>
                </div>

                <div className='grid grid-cols-6 gap-8 mt-4'>
                    <div className='col-span-4'>
                        <ProductDescription />
                        <RatingBox />
                    </div>
                    <div className='col-span-2'>
                        <CatalogSectionDetail />
                    </div>
                </div>
            </div>

        </section>

    )
}

export default ProductDetailPage