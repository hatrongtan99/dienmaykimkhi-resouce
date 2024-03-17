import OrdersContexProvider from "@/context/users/OrdersContexProvider";
import React, { ReactNode } from "react";

const layout = ({ children }: { children: ReactNode }) => {
    return <OrdersContexProvider>{children}</OrdersContexProvider>;
};

export default layout;
