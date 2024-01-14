import { getFileMediaByIdOption } from "@/api/media/media.queryOption";
import { getDetailProductBySlugOption } from "@/api/product/product.queryOption";
import BreadCrumb from "@/components/layout/BreadCrumb";
import CatalogSectionDetail from "@/components/product/detailPage/CatalogSectionDetail";
import NameSectionDetail from "@/components/product/detailPage/NameSectionDetail";
import PriceDetailProduct from "@/components/product/detailPage/PriceDetailProduct";
import ProductDescription from "@/components/product/detailPage/ProductDescription";
import RatingBox from "@/components/product/detailPage/RatingBox";
import SlideDetailProduct from "@/components/product/detailPage/SlideDetailProduct";
import {
    HydrationBoundary,
    QueryClient,
    dehydrate,
} from "@tanstack/react-query";
import BtnCartGroup from "./BtnCartGroup";
import { getAttributeProductByIdOption } from "@/api/attr/productAttribute.queryOption";

const imagesUrl = [
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-1-1639707151.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-3-1639707174.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-8-1639707518.jpg",
    "/images/products/500x-may-khoan-van-vit-dung-pin-18v-makita-df488dwe-1639706758.jpeg",
];

const ProductDetailPage = async ({
    params: { productSlug },
}: {
    params: { productSlug: string };
}) => {
    const queryClient = new QueryClient();

    // fetch data
    const detailProduct = await queryClient.fetchQuery(
        getDetailProductBySlugOption({ productSlug })
    );
    // imgs
    const imagesUrl: string[] = [];
    for (const idImage of detailProduct.images) {
        const { url } = await queryClient.fetchQuery(
            getFileMediaByIdOption({ id: idImage })
        );
        imagesUrl.push(url);
    }

    const attributesProduct = await queryClient.fetchQuery(
        getAttributeProductByIdOption({ id: detailProduct.id })
    );

    return (
        <HydrationBoundary state={dehydrate(queryClient)}>
            <section className="container">
                <BreadCrumb />
                <div className="bg-white p-4">
                    <div className="grid grid-cols-7 gap-8">
                        <div className="col-span-3">
                            <SlideDetailProduct images={imagesUrl} />
                        </div>

                        <div className="col-span-4">
                            <div>
                                <NameSectionDetail name={detailProduct.name} />
                                <PriceDetailProduct
                                    brand={{ name: "makita", slug: "makita" }}
                                    guarantee={detailProduct.guarantee}
                                    sku={detailProduct.sku}
                                    isInstock={detailProduct.isAvailInStock}
                                    price={detailProduct.price}
                                />
                                <BtnCartGroup />
                            </div>
                        </div>
                    </div>

                    <div className="grid grid-cols-6 gap-8 mt-4">
                        <div className="col-span-4">
                            <ProductDescription />
                            <RatingBox />
                        </div>
                        <div className="col-span-2">
                            <CatalogSectionDetail
                                attributes={attributesProduct}
                            />
                        </div>
                    </div>
                </div>
            </section>
        </HydrationBoundary>
    );
};

export default ProductDetailPage;
