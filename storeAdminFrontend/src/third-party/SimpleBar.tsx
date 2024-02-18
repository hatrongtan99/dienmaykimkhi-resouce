// material-ui
import { alpha, styled } from "@mui/material/styles";
import { Box, SxProps, Theme } from "@mui/material";

// third-party
import SimpleBar from "simplebar-react";
import { BrowserView, MobileView } from "react-device-detect";
import { ReactNode } from "react";

// root style
const RootStyle = styled(BrowserView)({
    flexGrow: 1,
    height: "100%",
    overflow: "hidden",
});

// scroll bar wrapper
const SimpleBarStyle = styled(SimpleBar)(({ theme }) => ({
    maxHeight: "100%",
    "& .simplebar-scrollbar": {
        "&:before": {
            backgroundColor: alpha(theme.palette.grey[500], 0.48),
        },
        "&.simplebar-visible:before": {
            opacity: 1,
        },
    },
    "& .simplebar-track.simplebar-vertical": {
        width: 10,
    },
    "& .simplebar-track.simplebar-horizontal .simplebar-scrollbar": {
        height: 6,
    },
    "& .simplebar-mask": {
        zIndex: "inherit",
    },
}));

interface SimpleBarScroll {
    children: ReactNode;
    sx: SxProps<Theme>;
}

export default function SimpleBarScroll({
    children,
    sx,
    ...props
}: SimpleBarScroll) {
    return (
        <>
            <RootStyle>
                <SimpleBarStyle clickOnTrack={false} sx={sx} {...props}>
                    {children}
                </SimpleBarStyle>
            </RootStyle>
            <MobileView>
                <Box sx={{ overflowX: "auto", ...sx }} {...props}>
                    {children}
                </Box>
            </MobileView>
        </>
    );
}
