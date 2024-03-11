import { MenuItems } from "@src/layout/mainLayout/Drawer/DrawerContent/Navigation";
import { BiCategoryAlt, BiSolidDiscount } from "react-icons/bi";
import { CiDiscount1 } from "react-icons/ci";
import { FaLinode } from "react-icons/fa";
import { TbBrandAirtable, TbShoppingCartDollar } from "react-icons/tb";

export default {
    id: "Manage",
    title: "Manage",
    type: "group",
    children: [
        {
            id: "Products",
            title: "Product",
            type: "item",
            icon: FaLinode,
            url: "/manage/products",
        },
        {
            id: "Categories",
            title: "Categories",
            type: "item",
            icon: BiCategoryAlt,
            url: "/manage/categories",
        },
        {
            id: "Brand",
            title: "Brand",
            type: "item",
            icon: TbBrandAirtable,
            url: "/manage/brands",
        },
        {
            id: "Orders",
            title: "Orders",
            type: "item",
            icon: TbShoppingCartDollar,
            url: "/manage/orders",
        },
        {
            id: "Promotions",
            title: "Promotions",
            type: "collapse",
            icon: BiSolidDiscount,
            children: [
                {
                    id: "Promotions-product",
                    title: "Promotion products",
                    type: "item",
                    icon: CiDiscount1,
                    url: "/manage/promotions/code",
                },
                {
                    id: "Promotions-code",
                    title: "Promotion code",
                    type: "item",
                    icon: CiDiscount1,
                    url: "/manage/promotions/products",
                },
            ],
        },
    ],
} as MenuItems;
