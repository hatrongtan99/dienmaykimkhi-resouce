import {
    UndefinedInitialDataOptions,
    queryOptions,
} from "@tanstack/react-query";
import {
    getDetailProductBySlug,
    getProductByBrandSlug,
    getProductByCategorySlug,
} from "./product.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { ProductResponseWithPage } from "@/types/product.type";

export const getDetailProductBySlugOption = ({
    productSlug,
}: {
    productSlug: string;
} & CustomUndefinedInitialDataOptions<ProductResponseWithPage>) => {
    return queryOptions({
        queryKey: ["detail-product-by-slug", { productSlug }],
        queryFn: () => getDetailProductBySlug({ slug: productSlug }),
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
