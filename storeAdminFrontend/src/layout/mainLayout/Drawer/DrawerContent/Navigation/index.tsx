// material-ui
import { Box, Typography } from "@mui/material";

import NavGroup from "./NavGroup";
import menuItems from "@src/menuItems";

export interface MenuItems {
    id: string;
    title: string;
    type: "group" | "item" | "collapse";
    url?: string;
    icon?: any;
    target?: boolean;
    children?: MenuItems[];
    disabled?: boolean;
}

const Navigation = () => {
    const navGroups = menuItems.map((item) => {
        switch (item.type) {
            case "group":
                return <NavGroup key={item.id} item={item} level={1} />;
            default:
                return (
                    <Typography
                        key={item.id}
                        variant="h6"
                        color="error"
                        align="center"
                    >
                        Fix - Navigation Group
                    </Typography>
                );
        }
    });

    return <Box sx={{ pt: 2 }}>{navGroups}</Box>;
};

export default Navigation;
