import { Box, Typography } from "@mui/material";

interface TagStatusProps {
    label: string;
    color?: "success" | "error" | "info" | "warning";
}

const TagStatus = ({ label, color = "success" }: TagStatusProps) => {
    return (
        <Box
            sx={{
                display: "inline-block",
                borderRadius: "10px",
                color: `${color}.lighter`,
                backgroundColor: `${color}.main`,
                padding: "4px 8px",
            }}
        >
            <Typography variant="subtitle2">{label}</Typography>
        </Box>
    );
};

export default TagStatus;
