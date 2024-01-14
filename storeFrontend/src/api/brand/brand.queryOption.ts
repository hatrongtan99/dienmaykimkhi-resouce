import { QueryOptions, queryOptions } from "@tanstack/react-query";
import { getAllBrand } from "./brand.api";

export const getAllBrandOption = ({}: {} & QueryOptions = {}) => {
    return queryOptions({
        queryKey: ["all-brand"],
        queryFn: getAllBrand,
    });
};
