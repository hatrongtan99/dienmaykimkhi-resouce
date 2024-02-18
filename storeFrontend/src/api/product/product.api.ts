import customFetch from "..";
import config from "@/constants";
import {
    ProductDetailResponse,
    ProductLineCartDetail,
    ProductResponseWithPage,
    StatusProductInStock,
} from "@/types/products/product.type";

const BASE_URL_PRODUCT_API =
    config.BASE_URL_BFF + "/products/bff-customer/products";

export const getDetailProductBySlug = ({ slug }: { slug: string }) => {
    return customFetch<ProductDetailResponse>(
        BASE_URL_PRODUCT_API + "/" + slug
    );
};

export const getStatusInStock = ({ productId }: { productId: number }) => {
    return customFetch<StatusProductInStock>(
        BASE_URL_PRODUCT_API + `/status-in-stock/${productId}`,
        {
            cache: "no-cache",
        }
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

export const getProductLineCartDetail = ({
    productId,
}: {
    productId: number;
}) => {
    return customFetch<ProductLineCartDetail>(
        BASE_URL_PRODUCT_API + "/cart-item/" + productId
    );
};
