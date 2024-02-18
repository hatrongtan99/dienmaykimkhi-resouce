import { MenuItems } from "@src/layout/mainLayout/Drawer/DrawerContent/Navigation";
import { FaUsers } from "react-icons/fa";

export default {
    id: "Customer",
    title: "Customer",
    type: "group",
    children: [
        {
            id: "Users",
            title: "Users",
            type: "item",
            icon: FaUsers,
        },
    ],
} as MenuItems;
