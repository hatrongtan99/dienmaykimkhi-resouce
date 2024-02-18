"use client";

import { getCurrentUser } from "@/api/user/user.api";
import { AuthContext } from "@/context/AuthContextProvider";
import { useContext } from "react";
import UserAvatar from "./UserAvatar";

const AuthCredencial = () => {
    const { setAuth, auth } = useContext(AuthContext);

    const handleLogin = () => {
        const newWindow = window.open(
            "http://localhost:8080/oauth2/authorization/api-client",
            "_blank",
            "width=600, height=500"
        );
        if (newWindow) {
            const timer = setInterval(async () => {
                if (newWindow.closed) {
                    try {
                        const res = await getCurrentUser();
                        console.log({ res });
                        setAuth(res);
                    } catch (error) {
                        console.log(error);
                    }
                    if (timer) {
                        clearInterval(timer);
                    }
                }
            }, 500);
        }
    };

    return (
        <>
            {auth ? (
                <UserAvatar auth={auth} />
            ) : (
                <div className="flex items-center content-center text-white text-sm p-[2px] cursor-pointer">
                    <p className="hover:text-[rgb(210,210,210)]">Đăng ký</p>
                    <span className="h-[calc(var(--header-height-34px))] w-[1px] bg-white mx-2"></span>
                    <p
                        className="hover:text-[rgb(210,210,210)]"
                        onClick={handleLogin}
                    >
                        Đăng nhập
                    </p>
                </div>
            )}
        </>
    );
};

export default AuthCredencial;
