import Loadable from "@src/components/Loadable";
import MainLayout from "@src/layout/mainLayout";
import { lazy } from "react";
import { RouteObject } from "react-router-dom";

const UsersPage = Loadable(lazy(() => import("@src/pages/customers/Users")));

export default {
    id: "cusstomers-route",
    path: "customers",
    element: <MainLayout />,
    children: [{ path: "/", element: <UsersPage /> }],
} as RouteObject;
