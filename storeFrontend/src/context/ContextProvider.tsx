"use client";

import { ReactNode } from "react";
import RouterConftextProvider from "./RouterContextProvider";
import AuthContextProvider from "./AuthContextProvider";
import CartContextProvider from "./CartContextProvider";
import AddressContextProvider from "./users/AddressContextProvider";

const ContextProvider = ({ children }: { children: ReactNode }) => {
    return (
        <AuthContextProvider>
            <RouterConftextProvider>
                <CartContextProvider>
                    <AddressContextProvider>{children}</AddressContextProvider>
                </CartContextProvider>
            </RouterConftextProvider>
        </AuthContextProvider>
    );
};

export default ContextProvider;
