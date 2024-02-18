import { useContext, useEffect } from "react";
import { Outlet } from "react-router-dom";

// material-ui
import { useTheme } from "@mui/material/styles";
import { Box, Toolbar, useMediaQuery } from "@mui/material";

// project import
import Header from "./header";

// types
import MainDrawer from "./Drawer";
import { MenuContext } from "@src/context/MenuProvider";

const MainLayout = () => {
    const theme = useTheme();
    const matchDownLG = useMediaQuery(theme.breakpoints.down("lg"));

    // drawer toggler
    const { openDrawer, setOpenDrawer } = useContext(MenuContext);

    const handleDrawerToggle = () => {
        setOpenDrawer(!openDrawer);
    };

    // set media wise responsive drawer
    useEffect(() => {
        setOpenDrawer(!matchDownLG);
    }, [matchDownLG]);

    return (
        <Box sx={{ display: "flex", width: "100%" }}>
            <Header open={openDrawer} handleDrawerToggle={handleDrawerToggle} />
            <MainDrawer
                open={openDrawer}
                handleDrawerToggle={handleDrawerToggle}
            />
            <Box
                component="main"
                sx={{ width: "100%", flexGrow: 1, minHeight: "100vh" }}
            >
                <Toolbar />
                <Outlet />
            </Box>
        </Box>
    );
};

export default MainLayout;
