import customFetch from "..";
import config from "@/constants";
import { BrandResponse } from "@/types/brand.type";

const BASE_URL_BRAND_API =
    config.BASE_URL_BFF + "/products/bff-customer/brands";

export const getAllBrand = () => {
    return customFetch<BrandResponse[]>(BASE_URL_BRAND_API);
};
