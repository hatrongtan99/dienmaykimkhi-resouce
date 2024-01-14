"use client";
import { useRouter } from "next/navigation";
import React from "react";

const ChildCategoryErrorPage = () => {
    const router = useRouter();
    router.replace("/");
    return null;
};

export default ChildCategoryErrorPage;
