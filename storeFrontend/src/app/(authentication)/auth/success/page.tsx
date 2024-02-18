"use client";

import { useEffect } from "react";

const AuthSuccessPage = () => {
    useEffect(() => {
        setTimeout(() => {
            window.close();
        }, 1000);
    });
    return <div>AuthSuccessPage</div>;
};
export default AuthSuccessPage;
