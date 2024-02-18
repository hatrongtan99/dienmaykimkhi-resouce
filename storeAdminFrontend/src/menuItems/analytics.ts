import { MenuItems } from "@src/layout/mainLayout/Drawer/DrawerContent/Navigation";

import { GrDocumentText } from "react-icons/gr";
import { BsCartCheck } from "react-icons/bs";
import {
    MdOutlineInventory,
    MdOutlineProductionQuantityLimits,
} from "react-icons/md";

export default {
    id: "Analytics",
    title: "Analytics",
    type: "group",
    children: [
        {
            id: "Overview",
            title: "Overview",
            type: "item",
            icon: GrDocumentText,
        },
        {
            id: "Product",
            title: "Product",
            type: "item",
            icon: MdOutlineProductionQuantityLimits,
        },
        {
            id: "Order",
            title: "Order",
            type: "item",
            icon: BsCartCheck,
        },
        {
            id: "Stock",
            title: "Stock",
            type: "item",
            icon: MdOutlineInventory,
        },
    ],
} as MenuItems;
