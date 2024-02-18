"use client";

import { AuthContext } from "@/context/AuthContextProvider";
import { ReactNode, useContext, useEffect } from "react";

const RequiredAuthLayout = ({ children }: { children: ReactNode }) => {
    const { auth, setAuth } = useContext(AuthContext);
    useEffect(() => {
        if (!auth) {
            const newWindow = window.open(
                "http://localhost:8080/oauth2/authorization/api-client",
                "_blank",
                "width=600, height=500"
            );
        }
    }, [auth]);

    if (!auth) {
        return <>loadding....</>;
    } else {
        return <>{children}</>;
    }
};

export default RequiredAuthLayout;
