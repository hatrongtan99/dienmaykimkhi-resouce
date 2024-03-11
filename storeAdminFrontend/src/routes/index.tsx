import { Link, RouteObject, useRoutes } from "react-router-dom";
import manageRoute from "./manage.route";
import Loadable from "@src/components/Loadable";
import { lazy } from "react";
import MainLayout from "@src/layout/mainLayout";

const HomePage = Loadable(lazy(() => import("@src/pages/Home")));

const NotFoundRoutest: RouteObject = {
    path: "/*",
    element: (
        <>
            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                }}
            >
                <img
                    src="https://cdn2.hubspot.net/hubfs/242200/shutterstock_774749455.jpg"
                    alt="not-found"
                    style={{ width: "60%" }}
                />
                <Link to="/">Go Home</Link>
            </div>
        </>
    ),
};

const Routes = () =>
    useRoutes([
        {
            id: "homePage",
            path: "/",
            element: <MainLayout />,
            children: [{ path: "/", element: <HomePage /> }],
        },
        manageRoute,
        NotFoundRoutest,
    ]);

export default Routes;
