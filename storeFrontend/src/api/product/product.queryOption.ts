import { queryOptions } from "@tanstack/react-query";
import {
    getDetailProductBySlug,
    getProductByBrandSlug,
    getProductByCategorySlug,
    getProductLineCartDetail,
    getStatusInStock,
} from "./product.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import {
    ProductDetailResponse,
    ProductLineCartDetail,
    ProductResponseWithPage,
    StatusProductInStock,
} from "@/types/products/product.type";

export const getDetailProductBySlugOption = ({
    productSlug,
    ...options
}: {
    productSlug: string;
} & CustomUndefinedInitialDataOptions<ProductDetailResponse>) => {
    return queryOptions({
        queryKey: ["detail-product-by-slug", { productSlug }],
        queryFn: () => getDetailProductBySlug({ slug: productSlug }),
        ...options,
    });
};

export const getStatusInStockOptions = ({
    productId,
    ...options
}: {
    productId: number;
} & CustomUndefinedInitialDataOptions<StatusProductInStock>) => {
    return queryOptions({
        queryKey: ["get-status-product-in-stock", { productId }],
        queryFn: () => getStatusInStock({ productId }),
        ...options,
    });
};

export const getProductByBrandSlugOption = ({
    brandSlug,
    params = "",
    ...option
}: {
    brandSlug: string;
    params?: string;
} & CustomUndefinedInitialDataOptions<ProductResponseWithPage>) => {
    return queryOptions({
        queryKey: ["list-product-by-brand-slug", { brandSlug, params }],
        queryFn: () => getProductByBrandSlug({ brandSlug, params }),
        ...option,
    });
};

export const getProductByCategorySlugOption = ({
    categorySlug,
    params = "",
    ...option
}: {
    categorySlug: string;
    params?: string;
} & CustomUndefinedInitialDataOptions<ProductResponseWithPage>) => {
    return queryOptions({
        queryKey: ["list-product-by-cate-slug", { categorySlug, params }],
        queryFn: () => getProductByCategorySlug({ categorySlug, params }),
        ...option,
    });
};

export const getProductLineCartDetailOption = ({
    productId,
    ...options
}: {
    productId: number;
} & CustomUndefinedInitialDataOptions<ProductLineCartDetail>) => {
    return queryOptions({
        queryKey: ["get-product-line-cart", { productId }],
        queryFn: () => getProductLineCartDetail({ productId }),
        ...options,
    });
};
