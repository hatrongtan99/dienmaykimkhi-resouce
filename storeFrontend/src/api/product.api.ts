import { ProductDetailResponse } from "@/types/product.type";
import customFetch from ".";

const BASE_URL_PRODUCT_API = "/products/bff-customer/products";

export const getDetailProductBySlug = (slug: string) => {
    return customFetch<ProductDetailResponse>(
        BASE_URL_PRODUCT_API + "/" + slug
    );
};
