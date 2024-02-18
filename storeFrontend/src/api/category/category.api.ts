import customFetch from "..";
import config from "@/constants";
import {
    CategoriesChildResponse,
    CategoriesParentResponse,
} from "@/types/products/categories.type";

const BASE_URL_CATEGORIES_API =
    config.BASE_URL_BFF + "/products/bff-customer/categories";

export const getAllParentCategories = ({
    params = "",
}: {
    params?: string;
} = {}) => {
    return customFetch<CategoriesParentResponse>(
        BASE_URL_CATEGORIES_API + `/parents` + `?${params}`
    );
};

export const getAllChildCategoryByParentId = ({
    id,
    params = "",
}: {
    id: number;
    params?: string;
}) => {
    return customFetch<CategoriesChildResponse>(
        BASE_URL_CATEGORIES_API + `/all-child/${id}` + `?${params}`
    );
};

export const getAllChildCategoryByParentSlug = ({
    parentSlug,
    params = "",
}: {
    parentSlug: string;
    params?: string;
}) => {
    return customFetch<CategoriesChildResponse>(
        BASE_URL_CATEGORIES_API + `/all-child/slug/${parentSlug}` + `?${params}`
    );
};

export const getListChildCategoryByParentSlug = ({
    parentSlug,
    params = "",
}: {
    parentSlug: string;
    params?: string;
}) => {
    return customFetch<CategoriesChildResponse>(
        BASE_URL_CATEGORIES_API + `/child/slug/${parentSlug}` + `?${params}`
    );
};
