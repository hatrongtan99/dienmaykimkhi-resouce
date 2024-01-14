// query option

import {
    QueriesOptions,
    QueryOptions,
    queryOptions,
} from "@tanstack/react-query";
import {
    getAllParentCategories,
    getAllChildCategoryByParentId,
    getAllChildCategoryByParentSlug,
    getListChildCategoryByParentSlug,
} from "./category.api";

export const getListParentCategoryOption = ({
    params = "",
}: {
    params?: string;
} & QueryOptions = {}) => {
    return queryOptions({
        queryKey: ["all-parrent-categories"],
        queryFn: () => getAllParentCategories({ params }),
    });
};

export const getAllChildCategoryByParentIdOption = ({
    id,
    params = "",
}: {
    id: number;
    params?: string;
} & QueryOptions) => {
    return queryOptions({
        queryKey: ["list-all-child-by-parent-category-id", { id, params }],
        queryFn: () => getAllChildCategoryByParentId({ id, params }),
    });
};

export const getAllChildCategoryByParentSlugOption = ({
    parentSlug,
    params = "",
}: {
    parentSlug: string;
    params?: string;
} & QueryOptions) => {
    return queryOptions({
        queryKey: [
            "list-all-child-by-parent-category-slug",
            { parentSlug, params },
        ],
        queryFn: () => getAllChildCategoryByParentSlug({ parentSlug, params }),
    });
};

export const getChildCategoryByParentSlugOption = ({
    parentSlug,
    params = "",
}: {
    parentSlug: string;
    params?: string;
} & QueryOptions) => {
    return queryOptions({
        queryKey: [
            "list-child-by-parent-category-slug",
            { parentSlug, params },
        ],
        queryFn: () => getListChildCategoryByParentSlug({ parentSlug, params }),
    });
};
