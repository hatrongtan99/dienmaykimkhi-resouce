import { QueryOptions, queryOptions } from "@tanstack/react-query";
import { getAttributeProductById } from "./productAtrribute.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { ProductAtrributeResponse } from "@/types/products/attribute.type";

export const getAttributeProductByIdOption = ({
    id,
}: { id: number } & CustomUndefinedInitialDataOptions<
    ProductAtrributeResponse[]
>) => {
    return queryOptions({
        queryKey: ["get-attribute-product-by-id", { id }],
        queryFn: () => getAttributeProductById({ id }),
    });
};
