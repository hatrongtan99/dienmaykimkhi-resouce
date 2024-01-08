import { BrandResponse } from "@/types/brand.type";
import customFetch from ".";

const BASE_URL_BRAND_URL = "/products/bff-customer/brands";

export const getAllBrand = () => {
    return customFetch<BrandResponse[]>(BASE_URL_BRAND_URL);
};
