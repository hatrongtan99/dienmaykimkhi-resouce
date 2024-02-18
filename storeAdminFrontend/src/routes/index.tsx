import Loadable from "@src/components/Loadable";
import MainLayout from "@src/layout/mainLayout";
import { lazy } from "react";
import { useRoutes } from "react-router-dom";

const HomePage = Loadable(lazy(() => import("@src/pages/Home")));

const Routes = () =>
    useRoutes([
        {
            element: <MainLayout />,
            children: [{ path: "/", element: <HomePage /> }],
        },
    ]);

export default Routes;
