import {
    Checkbox,
    IconButton,
    Stack,
    Table,
    TableContainer,
    TableHead,
    TablePagination,
    TableRow,
    TableSortLabel,
    Toolbar,
    Tooltip,
    Typography,
    alpha,
} from "@mui/material";
import React, {
    ChangeEvent,
    MouseEvent,
    ReactElement,
    useMemo,
    useState,
} from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import { FaSort } from "react-icons/fa";
import { LiaSortSolid } from "react-icons/lia";

import StyledTableCell from "./StyledTableCell";
import DialogCustom from "../customs/DialogCustom";
// import DialogCustom from "./DialogCustom";

const descendingComparator = <T,>(a: T, b: T, orderBy: keyof T) => {
    if (a[orderBy] > b[orderBy]) {
        return -1;
    }
    if (a[orderBy] < b[orderBy]) {
        return 1;
    }
    return 0;
};

type Order = "asc" | "desc";

const getComparator = <Key extends keyof any>(
    order: Order | null,
    orderBy: Key
): ((
    a: { [key in Key]: string | number },
    b: { [key in Key]: string | number }
) => number) => {
    if (!order) {
        return (a, b) => 0;
    }
    return order == "desc"
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
};

const stableSort = <T,>(
    array: readonly T[],
    comparator: (a: T, b: T) => number
) => {
    const stabilizedThis = array.map((el, index) => [el, index] as [T, number]);
    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) {
            return order;
        }
        return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
};

export interface HeadCell {
    disablePadding?: boolean;
    id: string;
    label: string;
    numberic?: boolean;
    sortable?: boolean;
}

interface EnhancedTableProps<T> {
    numSelected: number;
    onRequestSort: (
        event: MouseEvent<unknown>,
        property: string | number | null
    ) => void;
    onSelectAllClick: (event: React.ChangeEvent<HTMLInputElement>) => void;
    order: Order | null;
    orderBy: string | number | null;
    rowCount: number;
    headCells: HeadCell[];
    checkbox?: boolean;
}

const EnhancedTableHead = <T,>({
    numSelected,
    onRequestSort,
    onSelectAllClick,
    order,
    orderBy,
    rowCount,
    headCells,
    checkbox,
}: EnhancedTableProps<T>) => {
    return (
        <TableHead>
            <TableRow>
                {checkbox && (
                    <StyledTableCell
                        padding="checkbox"
                        align="center"
                        sx={{
                            "&.MuiTableCell-head": {
                                backgroundColor: "#fff",
                            },
                        }}
                    >
                        <Checkbox
                            color="primary"
                            indeterminate={
                                numSelected > 0 && numSelected < rowCount
                            }
                            checked={rowCount > 0 && numSelected === rowCount}
                            onChange={onSelectAllClick}
                            size="small"
                        />
                    </StyledTableCell>
                )}
                {headCells.map((headCell, index) => {
                    return (
                        <StyledTableCell
                            key={headCell.id}
                            align={headCell.numberic ? "right" : "center"}
                            padding={
                                headCell.disablePadding ? "none" : "normal"
                            }
                            sortDirection={
                                orderBy == headCell.id && order ? order : false
                            }
                        >
                            {headCell.sortable ? (
                                <TableSortLabel
                                    active={orderBy === headCell.id}
                                    direction={
                                        orderBy === headCell.id && order
                                            ? order
                                            : undefined
                                    }
                                    onClick={(e) =>
                                        onRequestSort(e, headCell.id)
                                    }
                                    IconComponent={!order ? FaSort : undefined}
                                    sx={{
                                        ".MuiTableSortLabel-icon": {
                                            fill: "#fff",
                                            fontSize: "1rem",
                                        },
                                    }}
                                    style={{
                                        color: "#fff",
                                    }}
                                >
                                    <LiaSortSolid /> {headCell.label}
                                </TableSortLabel>
                            ) : (
                                <>{headCell.label}</>
                            )}
                        </StyledTableCell>
                    );
                })}
            </TableRow>
        </TableHead>
    );
};

// toolbar
function EnhancedTableToolbar({
    numSelected,
    title,
    rowCount,
    handleDeleteSelected,
}: {
    numSelected: number;
    title?: string;
    rowCount: number;
    handleDeleteSelected: () => void;
}) {
    return (
        <Toolbar
            sx={{
                pl: { sm: 2 },
                pr: { xs: 1, sm: 1 },
                ...(numSelected > 0 && {
                    bgcolor: (theme) =>
                        alpha(
                            theme.palette.primary.main,
                            theme.palette.action.activatedOpacity
                        ),
                }),
            }}
        >
            <Typography
                sx={{ flex: "1 1 100%" }}
                variant="h5"
                id="tableTitle"
                component="div"
            >
                {title}
            </Typography>
            {numSelected > 0 ? (
                <Stack direction={"row"} alignItems={"center"}>
                    <Typography
                        color="inherit"
                        variant="subtitle1"
                        component="p"
                        sx={{ whiteSpace: "nowrap" }}
                    >
                        {numSelected} Đã chọn
                    </Typography>
                    <Tooltip title="Xoá" placement="top">
                        <IconButton>
                            <DialogCustom
                                handlAgree={handleDeleteSelected}
                                dialogContent={
                                    numSelected === rowCount
                                        ? "Xác nhận xoá Tất cả?"
                                        : "Xác nhận xoá?"
                                }
                            >
                                <DeleteIcon />
                            </DialogCustom>
                        </IconButton>
                    </Tooltip>
                </Stack>
            ) : null}
        </Toolbar>
    );
}

// ========== body ===========/

interface ChildrenProps<T> {
    isSelected: (id: string | number) => boolean;
    visibleRows: T[];
    handleClickRow: (e: MouseEvent<unknown>, id: string | number) => void;
    page: number;
    rowsPerPage: number;
    numEmtyrow: number;
    selected: (string | number)[];
}

interface TableSortableProps<T extends { id: string | number }> {
    rows: T[];
    headCells: HeadCell[];
    children: (param: ChildrenProps<T>) => ReactElement;
    checkbox?: boolean;
    pagination?: boolean;
    title?: string;
    handleDeleteToolBar?: (selected: (number | string)[]) => void;
    rowPerPageInit?: number;
    maxHeight?: string;
}

const TableSortable = <T extends { id: string | number }>({
    rows,
    headCells,
    children,
    pagination = true,
    checkbox = true,
    title,
    handleDeleteToolBar,
    rowPerPageInit,
    maxHeight,
}: TableSortableProps<T>) => {
    const [order, setOrder] = useState<Order | null>(null);
    const [orderBy, setOrderBy] = useState<string | number | null>(null);
    const [selected, setSelected] = useState<(string | number)[]>([]);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(rowPerPageInit ?? 5);

    const handleRequestSort = (
        e: MouseEvent<unknown>,
        property: string | number | null
    ) => {
        if (property == orderBy) {
            setOrder(order == null ? "asc" : order == "asc" ? "desc" : null);
        } else {
            setOrder("asc");
        }
        setOrderBy(property);
    };

    const handleSelectAllClick = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.checked) {
            setSelected(rows.map((r) => r.id));
        } else {
            setSelected([]);
        }
    };

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (e: ChangeEvent<HTMLInputElement>) => {
        setRowsPerPage(parseInt(e.target.value, 10));
        setPage(0);
    };

    const isSelected = (id: string | number) => selected.indexOf(id) !== -1;

    const visibleRows: T[] = useMemo(() => {
        const result = stableSort(rows, getComparator(order, orderBy!));
        if (!pagination) return result;
        return result.slice(
            page * rowsPerPage,
            page * rowsPerPage + rowsPerPage
        );
    }, [page, order, orderBy, rowsPerPage, rows]);

    const handleClickRow = (e: MouseEvent<unknown>, id: string | number) => {
        const isSelected = selected.indexOf(id);
        if (isSelected == -1) {
            setSelected([...selected, id]);
        } else {
            setSelected((prev) => {
                return prev.filter((i) => i !== id);
            });
        }
    };

    const numEmtyrow =
        page > 0 ? Math.max(0, (page + 1) * rowsPerPage - rows.length) : 0;

    return (
        <>
            <TableContainer
                component={"div"}
                sx={{ minHeight: "260px", maxHeight }}
            >
                {checkbox && (
                    <EnhancedTableToolbar
                        title={title}
                        numSelected={selected.length}
                        rowCount={rows.length}
                        handleDeleteSelected={() => {
                            handleDeleteToolBar!(selected);
                            setSelected([]);
                        }}
                    />
                )}

                <Table sx={{ width: "100%" }} size={"small"} stickyHeader>
                    {/*  header table */}
                    <EnhancedTableHead
                        numSelected={selected.length}
                        order={order}
                        orderBy={orderBy}
                        onSelectAllClick={handleSelectAllClick}
                        onRequestSort={handleRequestSort}
                        checkbox={checkbox}
                        rowCount={rows.length}
                        headCells={headCells}
                    />
                    {children({
                        handleClickRow,
                        isSelected,
                        visibleRows,
                        page,
                        rowsPerPage,
                        numEmtyrow,
                        selected,
                    })}
                </Table>
            </TableContainer>
            {pagination && (
                <TablePagination
                    rowsPerPageOptions={[5, 10, 25]}
                    component="div"
                    count={rows.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onPageChange={handleChangePage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                    labelRowsPerPage="Số hàng trong trang:"
                    showFirstButton={true}
                    showLastButton={true}
                />
            )}
        </>
    );
};

export default TableSortable;
