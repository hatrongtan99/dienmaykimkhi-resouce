import { ProductAtrributeResponse } from "@/types/attribute.type";
import customFetch from "..";
import config from "@/constants";

const BASE_URL_ATTRIBUTE_API =
    config.BASE_URL_BFF + "/products/bff-customer/attribute";

export const getAttributeProductById = ({ id }: { id: number }) => {
    return customFetch<ProductAtrributeResponse[]>(
        BASE_URL_ATTRIBUTE_API + `/product/${id}`
    );
};
