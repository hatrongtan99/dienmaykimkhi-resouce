import customFetch from "..";
import config from "@/constants";
import {
    ProductDetailResponse,
    ProductResponseWithPage,
} from "@/types/product.type";

const BASE_URL_PRODUCT_API =
    config.BASE_URL_BFF + "/products/bff-customer/products";

export const getDetailProductBySlug = ({ slug }: { slug: string }) => {
    return customFetch<ProductDetailResponse>(
        BASE_URL_PRODUCT_API + "/" + slug
    );
};

export const getProductByBrandSlug = ({
    brandSlug,
    params = "",
}: {
    brandSlug: string;
    params?: string;
}) => {
    return customFetch<ProductResponseWithPage>(
        BASE_URL_PRODUCT_API + `/brand-slug/${brandSlug}` + `?${params}`
    );
};

export const getProductByCategorySlug = ({
    categorySlug,
    params = "",
}: {
    categorySlug: string;
    params?: string;
}) => {
    return customFetch<ProductResponseWithPage>(
        BASE_URL_PRODUCT_API + `/category-slug/${categorySlug}` + `?${params}`
    );
};
