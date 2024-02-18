import { Collapse } from "@mui/material";
import { useState } from "react";
import { MenuItems } from ".";
import NavGroup from "./NavGroup";
import NavItem from "./NavItem";

const NavCollapse = ({ item, level }: { item: MenuItems; level: number }) => {
    const [open, setOpen] = useState<boolean>(false);
    const handleClick = () => {
        setOpen(!open);
    };
    return (
        <>
            <NavItem
                item={item}
                level={level}
                key={item.id}
                collapse
                openChild={open}
                setOpenChild={handleClick}
            />
            <Collapse in={open} timeout="auto" unmountOnExit>
                <NavGroup item={item} level={level + 1} />
            </Collapse>
        </>
    );
};

export default NavCollapse;
