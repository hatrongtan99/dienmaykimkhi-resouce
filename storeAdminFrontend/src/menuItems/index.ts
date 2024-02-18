import { MenuItems } from "../layout/mainLayout/Drawer/DrawerContent/Navigation";

import analyticsMenu from "./analytics";
import manage from "./manage";
import customer from "./customer";
import configuration from "./configuration";

const menuItems: MenuItems[] = [analyticsMenu, manage, customer, configuration];

export default menuItems;
