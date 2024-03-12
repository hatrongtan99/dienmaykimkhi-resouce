import config from "@/constants";
import customFetch from "..";
import { PaymentOnlineResponse } from "@/types/payment/paymentOnline.type";

const BASE_URL_PAYMENT =
    config.BASE_URL_BFF + "/payments/bff-customer/payment-online";

export const paymentOnlineRequest = ({ orderId }: { orderId: number }) => {
    return customFetch<PaymentOnlineResponse>(
        BASE_URL_PAYMENT + `/${orderId}`,
        {
            method: "post",
        }
    );
};
