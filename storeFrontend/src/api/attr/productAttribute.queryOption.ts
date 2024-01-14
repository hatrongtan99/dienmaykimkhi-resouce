import { QueryOptions, queryOptions } from "@tanstack/react-query";
import { getAttributeProductById } from "./productAtrribute.api";

export const getAttributeProductByIdOption = ({
    id,
}: { id: number } & QueryOptions) => {
    return queryOptions({
        queryKey: ["get-attribute-product-by-id", { id }],
        queryFn: () => getAttributeProductById({ id }),
    });
};
