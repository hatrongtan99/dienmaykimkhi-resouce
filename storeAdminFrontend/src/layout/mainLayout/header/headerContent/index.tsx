// material-ui
import { Box, Theme, useMediaQuery } from "@mui/material";

// project import
import Search from "./Search";
import Profile from "./Profile";
import Notification from "./Notification";
import MobileSection from "./MobileSection";

const HeaderContent = () => {
    const matchesMd = useMediaQuery((theme: Theme) =>
        theme.breakpoints.down("md")
    );

    return (
        <>
            {!matchesMd && <Search />}
            {matchesMd && <Box sx={{ width: "100%", ml: 1 }} />}
            <Notification />
            {!matchesMd && <Profile />}
            {matchesMd && <MobileSection />}
        </>
    );
};

export default HeaderContent;
