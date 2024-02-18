import { ReactNode, forwardRef } from "react";

// material-ui
import { useTheme } from "@mui/material/styles";
import {
    Card,
    CardContent,
    CardHeader,
    SxProps,
    Theme,
} from "@mui/material";

// project import

// header style
const headerSX = {
    paddingTop: 1,
    paddingLeft: 1,
    paddingRight: 0,
    paddingBottom: 0,
    "& .MuiCardHeader-action": { m: "0px auto", alignSelf: "center" },
};

interface MainCardProps {
    border?: boolean;
    boxShadow?: boolean;
    contentSX?: SxProps<Theme> | undefined;
    divider?: boolean;
    elevation?: number;
    secondary?: ReactNode;
    shadow?: string;
    sx?: SxProps<Theme> | undefined;
    title?: "string" | ReactNode;
    content?: boolean;
    children: ReactNode;
}

const MainCard = forwardRef<HTMLDivElement, MainCardProps>(
    (
        {
            border = false,
            boxShadow,
            children,
            content = true,
            contentSX = {},
            elevation,
            secondary,
            shadow,
            sx = {},
            title,
            ...others
        },
        ref
    ) => {
        const theme = useTheme();
        boxShadow =
            theme.palette.mode === "dark" ? boxShadow || true : boxShadow;

        return (
            <Card
                elevation={elevation || 0}
                ref={ref}
                {...others}
                sx={{
                    border: border ? "1px solid" : "none",
                    borderRadius: 2,
                    // borderColor:
                    //     theme.palette.mode === "dark"
                    //         ? theme.palette.divider
                    //         : theme.palette.grey.A800,
                    // boxShadow:
                    //     boxShadow && (!border || theme.palette.mode === "dark")
                    //         ? shadow || theme.customShadows.z1
                    //         : "inherit",
                    // ":hover": {
                    //     boxShadow: boxShadow
                    //         ? shadow || theme.customShadows.z1
                    //         : "inherit",
                    // },
                    "& pre": {
                        m: 0,
                        // p: "16px !important",
                        fontFamily: theme.typography.fontFamily,
                        fontSize: "0.75rem",
                    },
                    ...sx,
                }}
            >
                {/* card header and action */}
                {title != undefined && (
                    <CardHeader
                        sx={headerSX}
                        titleTypographyProps={{ variant: "subtitle1" }}
                        title={title}
                        action={secondary}
                    />
                )}
                {/* card content */}
                {content && (
                    <CardContent sx={contentSX}>{children}</CardContent>
                )}
                {!content && children}
            </Card>
        );
    }
);

export default MainCard;
