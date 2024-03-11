import CheckoutContextProvider from "@/context/checkout/CheckoutContextProvider";
import React, { ReactNode } from "react";

const layout = ({ children }: { children: ReactNode }) => {
    return <CheckoutContextProvider>{children}</CheckoutContextProvider>;
};

export default layout;
