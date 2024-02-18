import { useTheme } from "@mui/material/styles";
import {
    AppBar,
    AppBarProps,
    IconButton,
    Toolbar,
    useMediaQuery,
} from "@mui/material";

import { MenuFoldOutlined, MenuUnfoldOutlined } from "@ant-design/icons";
import HeaderContent from "./headerContent";

import { styled } from "@mui/material/styles";

import { DRAWER_WIDTH } from "@src/config";

interface AppBarStyledProps {
    open: boolean;
}

const AppBarStyled = styled(AppBar, {
    shouldForwardProp: (prop) => prop !== "open",
})<AppBarStyledProps>(({ theme, open }) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(["width", "margin"], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    width: `calc(100% - ${60}px)`,
    ...(open && {
        marginLeft: DRAWER_WIDTH,
        width: `calc(100% - ${DRAWER_WIDTH}px)`,
        transition: theme.transitions.create(["width", "margin"], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

interface HeaderProps {
    open: boolean;
    handleDrawerToggle: () => void;
}

const Header = ({ open, handleDrawerToggle }: HeaderProps) => {
    const theme = useTheme();
    const matchDownMD = useMediaQuery(theme.breakpoints.down("lg"));

    // common header
    const mainHeader = (
        <Toolbar>
            <IconButton
                onClick={handleDrawerToggle}
                edge="start"
                color="secondary"
                sx={{
                    color: "text.primary",
                    bgcolor: open ? "grey.200" : "grey.100",
                    ml: { xs: 0, lg: -2 },
                }}
            >
                {!open ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            </IconButton>
            <HeaderContent />
        </Toolbar>
    );

    // app-bar params
    const appBar: AppBarProps = {
        position: "fixed",
        elevation: 0,
        color: "inherit",
        sx: {
            borderBottom: `1px solid ${theme.palette.divider}`,
            // boxShadow: theme.customShadows.z1,
        },
    };

    return (
        <>
            {!matchDownMD ? (
                <AppBarStyled open={open} {...appBar}>
                    {mainHeader}
                </AppBarStyled>
            ) : (
                <AppBar {...appBar}>{mainHeader}</AppBar>
            )}
        </>
    );
};

export default Header;
