import customFetch from "@/api";
import config from "@/constants";
import { PromotionCodeResponse } from "@/types/promotions/promotionCode.type";

const BASE_URL_PROMOTION_CODE_API =
    config.BASE_URL_BFF + "/promotions/bff-customer/promotion-code";

export const getDetailPromotionCode = ({
    promotionCode,
}: {
    promotionCode: string;
}) => {
    return customFetch<PromotionCodeResponse>(
        BASE_URL_PROMOTION_CODE_API + "/" + promotionCode
    );
};
