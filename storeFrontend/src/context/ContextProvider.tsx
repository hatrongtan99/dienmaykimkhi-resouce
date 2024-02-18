"use client";

import { ReactNode } from "react";
import RouterConftextProvider from "./RouterContextProvider";
import AuthContextProvider from "./AuthContextProvider";
import CartContextProvider from "./CartContextProvider";

const ContextProvider = ({ children }: { children: ReactNode }) => {
    return (
        <AuthContextProvider>
            <RouterConftextProvider>
                <CartContextProvider>{children}</CartContextProvider>
            </RouterConftextProvider>
        </AuthContextProvider>
    );
};

export default ContextProvider;
