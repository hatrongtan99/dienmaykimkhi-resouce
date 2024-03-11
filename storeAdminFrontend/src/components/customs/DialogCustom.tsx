import { ReactNode, useState } from "react";

import {
    Button,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogContentText,
    DialogActions,
    IconButton,
    Box,
} from "@mui/material";

interface DialogProps {
    titleDialog?: string;
    dialogContent?: string;
    titleButtonClose?: string;
    titleButtonAgree?: string;
    handlAgree: () => void;
    children: ReactNode;
    hasIcon?: boolean;
    isDisabledAction?: boolean;
}

const DialogCustom = ({
    titleDialog,
    dialogContent,
    titleButtonClose = "Huỷ",
    titleButtonAgree = "Xác nhận",
    handlAgree,
    children,
    hasIcon,
    isDisabledAction,
}: DialogProps) => {
    const [openDialog, setOpenDialog] = useState<boolean>(false);

    const hanldeOpenDialog = () => {
        setOpenDialog(true);
    };

    const handleCloseDialog = () => {
        setOpenDialog(false);
    };

    const handleAgreeButton = () => {
        handlAgree();
        handleCloseDialog();
    };
    return (
        <>
            {hasIcon ? (
                <IconButton size="small" onClick={hanldeOpenDialog}>
                    {children}
                </IconButton>
            ) : (
                <Box
                    onClick={
                        isDisabledAction ? handleCloseDialog : hanldeOpenDialog
                    }
                >
                    {children}
                </Box>
            )}

            <Dialog open={openDialog} onClose={handleCloseDialog}>
                {titleDialog && <DialogTitle>{titleDialog}</DialogTitle>}
                {dialogContent && (
                    <DialogContent>
                        <DialogContentText
                            sx={{ color: "black", textAlign: "justify" }}
                        >
                            {dialogContent}
                        </DialogContentText>
                    </DialogContent>
                )}
                <DialogActions>
                    <Button
                        size="small"
                        onClick={handleCloseDialog}
                        color="error"
                        variant="outlined"
                    >
                        {titleButtonClose}
                    </Button>
                    <Button
                        size="small"
                        onClick={handleAgreeButton}
                        variant="contained"
                    >
                        {titleButtonAgree}
                    </Button>
                </DialogActions>
            </Dialog>
        </>
    );
};

export default DialogCustom;
