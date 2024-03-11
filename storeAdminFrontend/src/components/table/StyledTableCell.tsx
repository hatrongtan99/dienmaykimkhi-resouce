import { TableCell, styled, tableCellClasses } from "@mui/material";

interface StyledTableCellProps {
    bold?: boolean;
}

const StyledTableCell = styled(TableCell, {
    shouldForwardProp: (prop) => prop != "bold",
})<StyledTableCellProps>(({ theme, bold = false }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: "rgb(83,82,237)",
        color: theme.palette.common.white,
        padding: 4,
        display: "sticky",
    },
    ...(bold
        ? {
              [`&.${tableCellClasses.body}`]: {
                  fontSize: 15,
                  fontWeight: 600,
                  padding: 4,
              },
          }
        : {
              [`&.${tableCellClasses.body}`]: {
                  fontSize: 14,
                  fontWeight: 500,
                  padding: 4,
              },
          }),
}));

export default StyledTableCell;
