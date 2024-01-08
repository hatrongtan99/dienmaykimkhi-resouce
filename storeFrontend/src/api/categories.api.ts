import {
    CategoriesChildResponse,
    CategoriesParentResponse,
} from "@/types/categories.type";
import customFetch from ".";
import { PageableParams } from "@/types";

const BASE_URL_CATEGORIES_API = "/products/bff-customer";

export const getAllParentCategories = ({
    page = 0,
    limit = 10,
}: PageableParams = {}) => {
    return customFetch<CategoriesParentResponse>(
        BASE_URL_CATEGORIES_API + `/parent?page=${page}&pageLimit=${limit}`
    );
};

export const listChildCategoryByParentId = ({
    id,
    page = 0,
    limit = 10,
}: PageableParams & { id: number }) => {
    return customFetch<CategoriesChildResponse>(
        BASE_URL_CATEGORIES_API + `/child/${id}?page=${page}&pageLimit=${limit}`
    );
};
