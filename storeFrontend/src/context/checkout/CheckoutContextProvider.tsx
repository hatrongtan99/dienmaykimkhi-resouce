"use client";
import {
    getDetailAddressDefaultOptions,
    getListAddressUserOptions,
} from "@/api/user/address/addressUser.queryOptions";
import { OrderRequest, PaymentMethod } from "@/types/orders/orders.type";
import { AddressUserResponse } from "@/types/users/addressUser.type";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import {
    ReactNode,
    createContext,
    useContext,
    useEffect,
    useState,
} from "react";
import { AuthContext } from "../AuthContextProvider";
import { CartContext } from "../CartContextProvider";

export const CheckoutContext = createContext(
    {} as {
        addressCheckout: AddressUserResponse | undefined;
        handleChangePaymentMethod: (method: PaymentMethod) => void;
        paymentMethod: PaymentMethod;
        getCheckoutBody: () => OrderRequest;
    }
);

const CheckoutContextProvider = ({ children }: { children: ReactNode }) => {
    const queryClient = useQueryClient();
    const { auth } = useContext(AuthContext);
    const { listDetailCartItem } = useContext(CartContext);
    const [paymentMethod, setPaymentMethod] = useState<PaymentMethod>(
        PaymentMethod.COD
    );

    const [addressCheckout, setAddressCheckout] =
        useState<AddressUserResponse>();

    const { data: addressDefault, error: errorAddressDefault } = useQuery(
        getDetailAddressDefaultOptions()
    );
    const { data: listAddress, refetch: enabledGetListAddress } = useQuery(
        getListAddressUserOptions({
            enabled: false,
            select: (data) => {
                return data.filter((i) => i.id != addressDefault?.id);
            },
        })
    );

    useEffect(() => {
        if (addressDefault) {
            setAddressCheckout(addressDefault);
        } else if (errorAddressDefault) {
            alert("Vui lòng cập nhật địa chỉ nhận hàng!");
        }
    }, [addressDefault]);

    // change paymentMethod
    const handleChangePaymentMethod = (method: PaymentMethod) => {
        setPaymentMethod(method);
    };

    const getCheckoutBody = (): OrderRequest => {
        if (addressCheckout && listDetailCartItem) {
            const body: OrderRequest = {
                fullName: addressCheckout.fullName,
                email: auth?.email ?? "",
                phoneNumber: addressCheckout.phoneNumber,
                note: "",
                addressLine1: addressCheckout.addressLine1,
                addressLine2: addressCheckout.addressLine2,
                addressLine3: addressCheckout.addressLine3,
                deliveryFee: 0,
                deliveryMethod: "ship",
                cartItems: listDetailCartItem.map((cart) => ({
                    productId: cart.productId,
                    productName: cart.productName,
                    discount: cart.discount,
                    price: cart.price,
                    quantity: cart.quantity,
                    thumbnailUrl: cart.thumbnailUrl,
                })),
                couponeCode: null,
                discount: null,
                totalPrice: 0,
                paymentMethod,
            };
            return body;
        } else {
            throw new Error("Gặp lỗi khi tạo đơn hàng, vui lòng thử lại.");
        }
    };
    return (
        <CheckoutContext.Provider
            value={{
                addressCheckout,
                handleChangePaymentMethod,
                paymentMethod,
                getCheckoutBody,
            }}
        >
            {children}
        </CheckoutContext.Provider>
    );
};

export default CheckoutContextProvider;
