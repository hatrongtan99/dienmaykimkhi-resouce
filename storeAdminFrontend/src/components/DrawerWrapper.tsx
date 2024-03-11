import { Box, Drawer } from "@mui/material";
import { ReactElement, useState } from "react";

interface ChildrenProps {
    handletoggleDrawer: () => void;
    isOpen: boolean;
}

interface DrawerWrapperProps {
    children: ({ handletoggleDrawer }: ChildrenProps) => ReactElement;
    actionElement: ({ handletoggleDrawer }: ChildrenProps) => ReactElement;
}

const DrawerWrapper = ({ children, actionElement }: DrawerWrapperProps) => {
    const [openDrawer, setOpenDrawer] = useState<boolean>(false);
    const handletoggleDrawer = () => {
        setOpenDrawer(!openDrawer);
    };
    return (
        <Box>
            {actionElement({ handletoggleDrawer, isOpen: openDrawer })}
            <Drawer
                anchor={"right"}
                open={openDrawer}
                onClose={handletoggleDrawer}
                sx={{ zIndex: 1300 }}
            >
                {children({ handletoggleDrawer, isOpen: openDrawer })}
            </Drawer>
        </Box>
    );
};

export default DrawerWrapper;
