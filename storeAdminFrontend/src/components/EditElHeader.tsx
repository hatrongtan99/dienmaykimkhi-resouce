import { IconButton, Stack, Typography } from "@mui/material";
import { TiDelete } from "react-icons/ti";

const EditElHeader = ({
    title,
    handleClose,
}: {
    title: string;
    handleClose: () => void;
}) => {
    return (
        <Stack
            direction={"row"}
            justifyContent={"space-between"}
            alignItems={"center"}
            sx={{ padding: "5px 10px" }}
        >
            <Typography variant="h4">{title}</Typography>
            <IconButton
                sx={{
                    "&:hover": {
                        bgcolor: "error.light",
                        color: "error.lighter",
                    },
                }}
                onClick={handleClose}
            >
                <TiDelete />
            </IconButton>
        </Stack>
    );
};

export default EditElHeader;
