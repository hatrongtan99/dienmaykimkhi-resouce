// @ts-nocheck
import { Theme, styled } from "@mui/material/styles";
import Drawer, { DrawerProps } from "@mui/material/Drawer";

// project import
import { DRAWER_WIDTH } from "@src/config";

const openedMixin = (theme: Theme) => ({
    width: DRAWER_WIDTH,
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
    }),
    overflowX: "hidden",
    boxShadow: "none",
});

const closedMixin = (theme: Theme) => ({
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: "hidden",
    width: 60,
    backgroundColor: theme.palette.secondary.light,
});

interface MiniDrawerStyledProp {
    open: boolean;
}

const MiniDrawerStyled = styled(Drawer, {
    shouldForwardProp: (prop) => prop !== "open",
})<MiniDrawerStyledProp>(({ theme, open }) => ({
    width: DRAWER_WIDTH,
    flexShrink: 0,
    whiteSpace: "nowrap",
    boxSizing: "border-box",
    borderRight: `1px solid ${theme.palette.divider}`,
    ...(open && {
        ...openedMixin(theme),
        "& .MuiDrawer-paper": openedMixin(theme),
    }),
    ...(!open && {
        ...closedMixin(theme),
        "& .MuiDrawer-paper": closedMixin(theme),
    }),
}));

export default MiniDrawerStyled;
