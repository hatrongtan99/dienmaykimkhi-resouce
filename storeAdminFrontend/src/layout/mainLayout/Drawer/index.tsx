// material-ui
import { useTheme } from "@mui/material/styles";
import { Box, Drawer, useMediaQuery } from "@mui/material";

// project import
import DrawerHeader from "./DrawerHeader";
import DrawerContent from "./DrawerContent";
import MiniDrawerStyled from "./MiniDrawerStyled";
import { DRAWER_WIDTH } from "@src/config";

interface MainDrawerProp {
    open: boolean;
    handleDrawerToggle: () => void;
}

const MainDrawer = ({ open, handleDrawerToggle }: MainDrawerProp) => {
    const theme = useTheme();
    const matchDownMD = useMediaQuery(theme.breakpoints.down("lg"));

    return (
        <Box component="nav" sx={{ flexShrink: { md: 0 }, zIndex: 1300 }}>
            {!matchDownMD ? (
                <MiniDrawerStyled variant="permanent" open={open}>
                    <DrawerHeader open={open} />
                    <DrawerContent />
                </MiniDrawerStyled>
            ) : (
                <Drawer
                    variant="temporary"
                    open={open}
                    onClose={handleDrawerToggle}
                    // ModalProps={{ keepMounted: true }}
                    sx={{
                        display: { xs: "block", lg: "none" },
                        "& .MuiDrawer-paper": {
                            boxSizing: "border-box",
                            width: DRAWER_WIDTH,
                            borderRight: `1px solid ${theme.palette.divider}`,
                            backgroundImage: "none",
                            boxShadow: "inherit",
                        },
                    }}
                >
                    {open && <DrawerHeader open={open} />}
                    {open && <DrawerContent />}
                </Drawer>
            )}
        </Box>
    );
};

export default MainDrawer;
