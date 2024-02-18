import { queryOptions } from "@tanstack/react-query";
import { getDetailPromotionProduct } from "./product.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { PromotionProductResponse } from "@/types/promotions/promotionProduct.type";

export const getDetailPromotionProductOptopns = ({
    productId,
    ...otp
}: {
    productId: number;
} & CustomUndefinedInitialDataOptions<PromotionProductResponse>) => {
    return queryOptions({
        queryKey: ["detail-promotion-product", { productId }],
        queryFn: () =>
            getDetailPromotionProduct({
                productId,
                date: new Date().toISOString(),
            }),
        ...otp,
    });
};
