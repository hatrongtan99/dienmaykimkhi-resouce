import { Box, List, Typography } from "@mui/material";

import NavItem from "./NavItem";
import { useContext } from "react";
import { MenuContext } from "@src/context/MenuProvider";
import NavCollapse from "./NavCollapse";
import { MenuItems } from ".";

const NavGroup = ({ item, level }: { item: MenuItems; level: number }) => {
    const { openDrawer } = useContext(MenuContext);

    const navCollapse = item.children?.map((menuItem: MenuItems) => {
        switch (menuItem.type) {
            case "collapse":
                return (
                    <NavCollapse
                        key={menuItem.id}
                        level={level}
                        item={menuItem}
                    />
                );
            case "item":
                return (
                    <NavItem key={menuItem.id} item={menuItem} level={level} />
                );
            default:
                return (
                    <Typography
                        key={menuItem.id}
                        variant="h6"
                        color="error"
                        align="center"
                    >
                        Fix - Group Collapse or Items
                    </Typography>
                );
        }
    });

    return (
        <List
            subheader={
                item.title &&
                item.type == "group" &&
                openDrawer && (
                    <Box sx={{ pl: 3, mb: 1.5, backgroundColor: "Highlight" }}>
                        <Typography variant="subtitle2">
                            {item.title}
                        </Typography>
                    </Box>
                )
            }
            sx={{ mb: openDrawer ? 1.5 : 0, py: 0, zIndex: 0 }}
        >
            {navCollapse}
        </List>
    );
};
export default NavGroup;
