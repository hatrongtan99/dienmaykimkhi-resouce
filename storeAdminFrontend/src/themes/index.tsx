import {
    CssBaseline,
    ThemeOptions,
    ThemeProvider as ThemeProviderMui,
    createTheme,
} from "@mui/material";
import { ReactNode, useContext, useMemo } from "react";
import Palette from "./palette";
import Typography from "./typography";
import { Typography as TypographyType } from "@mui/material/styles/createTypography";

const ThemeProvider = ({ children }: { children: ReactNode }) => {
    const theme = Palette("light");

    const themeTypography = Typography(
        `'Public Sans', sans-serif`
    ) as TypographyType;

    const themeOptions: ThemeOptions = useMemo(() => {
        return {
            breakpoints: {
                values: {
                    xs: 0,
                    sm: 768,
                    md: 1024,
                    lg: 1266,
                    xl: 1536,
                },
            },
            direction: "ltr",
            mixins: {
                toolbar: {
                    minHeight: 60,
                    paddingTop: 8,
                    paddingBottom: 8,
                },
            },
            palette: theme.palette,
            typography: themeTypography,
        };
    }, [theme]);
    const themes = createTheme(themeOptions);
    return (
        <ThemeProviderMui theme={themes}>
            <CssBaseline />
            {children}
        </ThemeProviderMui>
    );
};

export default ThemeProvider;
