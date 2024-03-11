import { getFileMediaByIdOption } from "@/api/media/media.queryOption";
import {
    getDetailProductBySlugOption,
    getStatusInStockOptions,
} from "@/api/product/product.queryOption";
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

const ProductDetailPage = async ({
    params: { productSlug },
}: {
    params: { productSlug: string };
}) => {
    const queryClient = new QueryClient();

    // fetch data
    const detailProduct = await queryClient.fetchQuery(
        getDetailProductBySlugOption({
            productSlug,
            refetchOnWindowFocus: false,
        })
    );
    // imgs
    const imagesUrl: string[] = [];
    for (const idImage of detailProduct.images) {
        const { url } = await queryClient.fetchQuery(
            getFileMediaByIdOption({
                id: idImage,
                refetchOnWindowFocus: false,
                staleTime: 1000 * 60 * 5,
            })
        );
        imagesUrl.push(url);
    }
    // catalog session
    const attributesProduct = await queryClient.fetchQuery(
        getAttributeProductByIdOption({
            id: detailProduct.id,
            refetchOnWindowFocus: false,
            staleTime: 1000 * 60 * 5,
        })
    );

    // status in stock
    const statusInStock = await queryClient.fetchQuery(
        getStatusInStockOptions({ productId: detailProduct.id, staleTime: 0 })
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
                                    isInstock={statusInStock.isInStock}
                                    price={detailProduct.price}
                                />
                                <BtnCartGroup
                                    productId={detailProduct.id}
                                    disableBtn={!statusInStock.isInStock}
                                />
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
