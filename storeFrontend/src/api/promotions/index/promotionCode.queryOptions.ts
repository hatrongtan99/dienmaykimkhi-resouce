import { CustomUndefinedInitialDataOptions } from "@/types";
import { queryOptions } from "@tanstack/react-query";
import { getDetailPromotionCode } from "./pomotionCode.api";
import { PromotionCodeResponse } from "@/types/promotions/promotionCode.type";

export const getDetailPromotionCodeOptions = ({
    promotionCode,
    ...otp
}: {
    promotionCode: string;
} & CustomUndefinedInitialDataOptions<PromotionCodeResponse>) => {
    return queryOptions({
        queryKey: [],
        queryFn: () => getDetailPromotionCode({ promotionCode }),
        ...otp,
    });
};
