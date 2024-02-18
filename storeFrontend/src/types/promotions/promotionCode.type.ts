export interface PromotionCodeResponse {
    id: number;
    name: string;
    description: string;
    couponCode: string;
    quantity: number;
    percentCoupon: number;
    minPriceAccept: number;
    maxAmount: number;
    startDate: string;
    endDate: string;
    isActive: boolean;
}
