import Loadable from "@src/components/Loadable";
import MainLayout from "@src/layout/mainLayout";
import { lazy } from "react";
import { RouteObject } from "react-router-dom";

// products
const ManageProductPage = Loadable(
    lazy(() => import("@src/pages/manage/products/ManageProduct"))
);

// categories
const ManageCategoriesPage = Loadable(
    lazy(() => import("@src/pages/manage/categories/ManageCategories"))
);

// brands
const ManageBrandsPage = Loadable(
    lazy(() => import("@src/pages/manage/brands/ManageBrands"))
);

// orders
const ManageOrdersPage = Loadable(
    lazy(() => import("@src/pages/manage/orders/Orders"))
);

// promotion
const ManagePromotionProductPage = Loadable(
    lazy(
        () =>
            import(
                "@src/pages/manage/promotion/promotionProduct/ManagePromotionProduct"
            )
    )
);

const ManagePromotionCodePage = Loadable(
    lazy(
        () =>
            import(
                "@src/pages/manage/promotion/promotionCode/ManagePromotionCode"
            )
    )
);

export default {
    id: "manage-route",
    path: "manage",
    element: <MainLayout />,
    children: [
        // product
        {
            path: "products",
            element: <ManageProductPage />,
        },
        // categories
        {
            path: "categories",
            element: <ManageCategoriesPage />,
        },
        // brands
        {
            path: "brands",
            element: <ManageBrandsPage />,
        },
        // orders
        {
            path: "orders",
            element: <ManageOrdersPage />,
        },
        // promotions
        {
            path: "promotions",
            children: [
                { path: "products", element: <ManagePromotionProductPage /> },
                { path: "code", element: <ManagePromotionCodePage /> },
            ],
        },
    ],
} as RouteObject;
