import { Image } from "@mui/icons-material";
import {
    Box,
    Button,
    Checkbox,
    Grid,
    IconButton,
    Stack,
    Switch,
    TableBody,
    TableCell,
    TableRow,
    Typography,
} from "@mui/material";
import DrawerWrapper from "@src/components/DrawerWrapper";
import EditElHeader from "@src/components/EditElHeader";
import Helmet from "@src/components/Helmet";
import TagStatus from "@src/components/TagStatus";
import AutocompleteAsync from "@src/components/customs/AutoCompleteAsync";
import StyledTableCell from "@src/components/table/StyledTableCell";
import TableSortable, {
    HeadCell,
} from "@src/components/table/TableSortableWithPagination";
import CreateOrUpdateProduct from "@src/layout/products/CreateOrUpdateProduct";
import { formatPriceDisplay } from "@src/utils";
import { FaEdit } from "react-icons/fa";
import { RiDeleteBin6Line } from "react-icons/ri";

const headCells: HeadCell[] = [
    { id: "name", label: "Products", sortable: true },
    { id: "category", label: "Category", sortable: true },
    { id: "updatedAt", label: "Update", sortable: true },
    { id: "price", label: "Price", sortable: true },
    { id: "inStock", label: "In Stock", sortable: true },
    { id: "status", label: "Status", sortable: true },
    { id: "isPublish", label: "Published" },
    { id: "action", label: "Action" },
];

const data = {
    name: "Nike Women's Race Running Shoe",
    category: "Máy khoan",
    thumbnail: "",
    updatedAt: "27/05/2022",
    price: 7900000,
    discount: 10,
    inStock: 100,
    status: "in stock",
    isPublish: true,
};

const ManageProduct = () => {
    return (
        <Helmet title="Manage products">
            <Grid container mx={2}>
                <Grid item xs={12} mb={0.5}>
                    <Stack
                        direction={"row"}
                        spacing={2}
                        sx={{
                            padding: "10px",
                            borderRadius: "5px",
                            backgroundColor: "secondary.200",
                        }}
                    >
                        <AutocompleteAsync
                            options={[
                                { title: "may khoan", id: 1 },
                                { title: "may khoan pin", id: 2 },
                            ]}
                            label="Categories"
                            isLoading={false}
                            sx={{ bgcolor: "white" }}
                        />
                        <AutocompleteAsync
                            options={[
                                { title: "may khoan", id: 1 },
                                { title: "may khoan pin", id: 2 },
                            ]}
                            label="Brands"
                            isLoading={false}
                            sx={{ bgcolor: "white" }}
                        />
                        <Button variant="contained">Filter</Button>
                        <Button variant="outlined">Reset</Button>
                    </Stack>
                </Grid>
                <Grid item xs={12}>
                    <TableSortable
                        headCells={headCells}
                        rows={new Array(20).fill(null).map((_, index) => ({
                            ...data,
                            id: index,
                        }))}
                        checkbox={true}
                        rowPerPageInit={10}
                        title="Products"
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
                                        const priceDiscount =
                                            row.discount > 0
                                                ? (row.price * row.discount) /
                                                  100
                                                : 0;
                                        return (
                                            <TableRow
                                                key={index}
                                                hover
                                                sx={{ height: 50 }}
                                            >
                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    <Checkbox
                                                        checked={isSelected(
                                                            row.id
                                                        )}
                                                        size={"small"}
                                                        onClick={(e) =>
                                                            handleClickRow(
                                                                e,
                                                                row.id
                                                            )
                                                        }
                                                    />
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
                                                        <Box
                                                            component="img"
                                                            sx={{
                                                                height: 80,
                                                                width: 80,
                                                            }}
                                                            alt="The house from the offer."
                                                            src="https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2"
                                                        />
                                                        <Stack marginLeft={2}>
                                                            <Typography variant="subtitle1">
                                                                {row.name}
                                                            </Typography>
                                                            <Box
                                                                component={
                                                                    "img"
                                                                }
                                                                sx={{
                                                                    width: 50,
                                                                    height: "auto",
                                                                }}
                                                                alt="brands"
                                                                src="https://maydochuyendung.com/img/uploads/cache_image/x35-makita-1584090120.png"
                                                            ></Box>
                                                        </Stack>
                                                    </Stack>
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    scope="row"
                                                    align="left"
                                                >
                                                    {row.category}
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    {row.updatedAt}
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    <Stack
                                                        direction={"column"}
                                                        spacing={2}
                                                    >
                                                        {priceDiscount > 0 && (
                                                            <Typography
                                                                sx={{
                                                                    textDecoration:
                                                                        "line-through",
                                                                }}
                                                            >
                                                                {formatPriceDisplay(
                                                                    row.price
                                                                )}
                                                            </Typography>
                                                        )}
                                                        <Typography
                                                            variant="subtitle1"
                                                            sx={{
                                                                color: "error.main",
                                                            }}
                                                        >
                                                            {formatPriceDisplay(
                                                                row.price -
                                                                    priceDiscount
                                                            )}
                                                        </Typography>
                                                    </Stack>
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    scope="row"
                                                    align="center"
                                                >
                                                    <Typography variant="subtitle1">
                                                        {row.inStock}
                                                    </Typography>
                                                </StyledTableCell>
                                                <StyledTableCell
                                                    align="center"
                                                    scope="row"
                                                >
                                                    <TagStatus
                                                        label={row.status}
                                                    />
                                                </StyledTableCell>

                                                <StyledTableCell
                                                    align="center"
                                                    scope="row"
                                                >
                                                    <Switch
                                                        checked={row.isPublish}
                                                    />
                                                </StyledTableCell>
                                                {/* action */}
                                                <StyledTableCell align="center">
                                                    <DrawerWrapper
                                                        actionElement={({
                                                            handletoggleDrawer,
                                                        }) => (
                                                            <Stack
                                                                direction={
                                                                    "row"
                                                                }
                                                            >
                                                                <IconButton
                                                                    size="small"
                                                                    onClick={
                                                                        handletoggleDrawer
                                                                    }
                                                                >
                                                                    <FaEdit />
                                                                </IconButton>
                                                                <IconButton size="small">
                                                                    <RiDeleteBin6Line />
                                                                </IconButton>
                                                            </Stack>
                                                        )}
                                                    >
                                                        {({
                                                            isOpen,
                                                            handletoggleDrawer,
                                                        }) => {
                                                            return (
                                                                <CreateOrUpdateProduct
                                                                    isOpen={
                                                                        isOpen
                                                                    }
                                                                    handleClose={
                                                                        handletoggleDrawer
                                                                    }
                                                                />
                                                            );
                                                        }}
                                                    </DrawerWrapper>
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
            ;
        </Helmet>
    );
};

export default ManageProduct;
