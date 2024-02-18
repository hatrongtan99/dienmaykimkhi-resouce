import { MenuItems } from "@src/layout/mainLayout/Drawer/DrawerContent/Navigation";
import { IoSettingsOutline } from "react-icons/io5";

export default {
    id: "Config",
    title: "Configuration",
    type: "group",
    children: [
        {
            id: "Settings",
            title: "Settings",
            icon: IoSettingsOutline,
            type: "item",
        },
    ],
} as MenuItems;
