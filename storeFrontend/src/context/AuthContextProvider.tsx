"use client";
import { UserAuthenticate } from "@/types/users/userInfo.type";
import { ReactNode, createContext, useState } from "react";

export const AuthContext = createContext(
    {} as {
        auth: UserAuthenticate | undefined;
        setAuth: React.Dispatch<UserAuthenticate | undefined>;
    }
);

const AuthContextProvider = ({ children }: { children: ReactNode }) => {
    const [auth, setAuth] = useState<{} & UserAuthenticate>();
    return (
        <AuthContext.Provider
            value={{
                auth,
                setAuth,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContextProvider;
