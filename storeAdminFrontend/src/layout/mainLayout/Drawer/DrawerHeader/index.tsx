import { styled } from "@mui/material/styles";
import { Box } from "@mui/material";

import { useTheme } from "@mui/material/styles";
import { Stack, Chip } from "@mui/material";

// styled
interface DrawerHeaderStyledProps {
    open: boolean;
}
const DrawerHeaderStyled = styled(Box, {
    shouldForwardProp: (prop) => prop !== "open",
})<DrawerHeaderStyledProps>(({ theme, open }) => ({
    ...theme.mixins.toolbar,
    display: "flex",
    alignItems: "center",
    justifyContent: open ? "flex-start" : "center",
    paddingLeft: theme.spacing(open ? 3 : 0),
}));

const DrawerHeader = ({ open }: { open: boolean }) => {
    const theme = useTheme();

    return (
        <DrawerHeaderStyled theme={theme} open={open}>
            <Stack direction="row" spacing={1} alignItems="center">
                {open && <>logo</>}

                <Chip
                    label={import.meta.env.VITE_APP_VERSION}
                    size="small"
                    sx={{
                        height: 16,
                        "& .MuiChip-label": { fontSize: "0.625rem", py: 0.25 },
                    }}
                    component="p"
                />
            </Stack>
        </DrawerHeaderStyled>
    );
};

export default DrawerHeader;
