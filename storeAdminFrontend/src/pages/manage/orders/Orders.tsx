import {
    Avatar,
    Box,
    Grid,
    IconButton,
    Stack,
    TableBody,
    TableCell,
    TableRow,
    Typography,
} from "@mui/material";
import Helmet from "@src/components/Helmet";
import TagStatus from "@src/components/TagStatus";
import StyledTableCell from "@src/components/table/StyledTableCell";
import TableSortable, {
    HeadCell,
} from "@src/components/table/TableSortableWithPagination";
import { formatPriceDisplay } from "@src/utils";
import { FaEdit } from "react-icons/fa";
import { RiDeleteBin6Line } from "react-icons/ri";

const headCells: HeadCell[] = [
    { id: "id", label: "Order ID", sortable: true },
    { id: "userName", label: "Customer", sortable: true },
    { id: "orderDate", label: "Order Date", sortable: true },
    { id: "status", label: "Status", sortable: true },
    { id: "total", label: "total", sortable: true },
    { id: "action", label: "Action" },
];

const data = {
    id: "INV020",
    name: "jonh doe",
    createAt: "25/12/2023",
    status: "Shipped",
    total: 7900000,
};

const Orders = () => {
    return (
        <Helmet title="Manage Orders">
            <Grid container>
                <Grid item xs={12}>
                    <TableSortable
                        headCells={headCells}
                        rows={new Array(20).fill(null).map((_, index) => ({
                            ...data,
                            id: index,
                        }))}
                        rowPerPageInit={10}
                        checkbox={false}
                    >
                        {({
                            visibleRows,
                            page,
                            rowsPerPage,
                            numEmtyrow,
                            isSelected,
                            handleClickRow,
                        }) => {
                            return (
                                <TableBody>
                                    {visibleRows.map((row, index) => {
                                        return (
                                            <TableRow
                                                key={index}
                                                hover
                                                sx={{ height: 70 }}
                                            >
                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    <Typography variant="subtitle1">
                                                        {row.id}
                                                    </Typography>
                                                </StyledTableCell>
                                                <StyledTableCell
                                                    scope="row"
                                                    align="left"
                                                    bold
                                                >
                                                    <Stack
                                                        direction={"row"}
                                                        alignItems={"center"}
                                                    >
                                                        <Avatar
                                                            alt="The house from the offer."
                                                            src="https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2"
                                                        />
                                                        <Stack marginLeft={2}>
                                                            <Typography variant="subtitle1">
                                                                {row.name}
                                                            </Typography>
                                                        </Stack>
                                                    </Stack>
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    {row.createAt}
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    align="center"
                                                    scope="center"
                                                >
                                                    <TagStatus
                                                        label={row.status}
                                                    />
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    align="center"
                                                    scope="center"
                                                >
                                                    {formatPriceDisplay(
                                                        row.total
                                                    )}
                                                </StyledTableCell>
                                                {/* action */}
                                                <StyledTableCell align="center">
                                                    <Stack direction={"row"}>
                                                        <IconButton size="small">
                                                            <FaEdit />
                                                        </IconButton>
                                                        <IconButton size="small">
                                                            <RiDeleteBin6Line />
                                                        </IconButton>
                                                    </Stack>
                                                </StyledTableCell>
                                            </TableRow>
                                        );
                                    })}

                                    {/* empty rows */}
                                    {numEmtyrow > 0 && (
                                        <TableRow
                                            style={{
                                                height: 50 * numEmtyrow,
                                            }}
                                        >
                                            <TableCell />
                                        </TableRow>
                                    )}
                                </TableBody>
                            );
                        }}
                    </TableSortable>
                </Grid>
            </Grid>
        </Helmet>
    );
};

export default Orders;
