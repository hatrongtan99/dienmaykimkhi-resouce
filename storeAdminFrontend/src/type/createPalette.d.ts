import {
    Palette as MuiPallete,
    PaletteOptions as MuiPaletteOptions,
} from "@mui/material/styles/createPalette";

declare module "@mui/material/styles/createPalette" {
    export interface SimplePaletteColorOptions {
        lighter: string;
        darker: string;
        A300?: string;
    }
    export interface PaletteColor {
        lighter: string;
        darker: string;
        A300?: string;
    }
}
