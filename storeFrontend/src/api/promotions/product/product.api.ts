import customFetch from "@/api";
import config from "@/constants";
import { PromotionProductResponse } from "@/types/promotions/promotionProduct.type";

const BASE_URL_PROMOTION_PRODUCT_API =
    config.BASE_URL_BFF + "/promotions/bff-customer/products";

export const getDetailPromotionProduct = ({
    productId,
    date,
}: {
    productId: number;
    date: string;
}) => {
    return customFetch<PromotionProductResponse>(
        BASE_URL_PROMOTION_PRODUCT_API + `/${productId}/${date}`
    );
};
