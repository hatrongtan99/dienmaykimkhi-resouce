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
        },
        {
            id: "Categories",
            title: "Categories",
            type: "item",
            icon: BiCategoryAlt,
        },
        {
            id: "Brand",
            title: "Brand",
            type: "item",
            icon: TbBrandAirtable,
        },
        {
            id: "Orders",
            title: "Orders",
            type: "item",
            icon: TbShoppingCartDollar,
        },
        {
            id: "Promotions",
            title: "Promotions",
            type: "collapse",
            icon: BiSolidDiscount,
            children: [
                {
                    id: "Promotions-code",
                    title: "Promotion code",
                    type: "item",
                    icon: CiDiscount1,
                },
                {
                    id: "Promotions-product",
                    title: "Promotion products",
                    type: "item",
                    icon: CiDiscount1,
                },
            ],
        },
    ],
} as MenuItems;
