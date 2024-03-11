import {
    Autocomplete,
    Box,
    Button,
    Chip,
    Divider,
    Stack,
    TextField,
} from "@mui/material";
import EditElHeader from "@src/components/EditElHeader";
import CustomizedTreeView from "@src/components/customs/CustomizedTreeView";
import Input from "@src/components/customs/form/Input";
import InputImages from "@src/components/customs/form/InputImages";
import LableWrapper from "@src/components/customs/form/LableWrapper";
import SimpleBarScroll from "@src/third-party/SimpleBar";
import { useForm } from "react-hook-form";

interface CreateOrUpdateProductProps {
    isOpen?: boolean;
    handleClose: () => void;
}

const CreateOrUpdateProduct = ({
    isOpen,
    handleClose,
}: CreateOrUpdateProductProps) => {
    const { register, handleSubmit } = useForm();

    if (!isOpen) return null;

    return (
        <form
            onSubmit={handleSubmit((data) => {
                console.log(data);
            })}
            style={{ height: "100%" }}
        >
            <Stack
                direction={"column"}
                justifyContent={"space-between"}
                height={"100%"}
                width={"1000px"}
            >
                <EditElHeader
                    title="Update Product"
                    handleClose={handleClose}
                />
                <Divider />

                <SimpleBarScroll sx={{ flexGrow: 1 }}>
                    <div className="px-4 py-6">
                        <LableWrapper name="productName" label="Product Name">
                            <Input name="productName" register={register} />
                        </LableWrapper>

                        <LableWrapper label="Description" name="Description">
                            <Input
                                name="Description"
                                register={register}
                                as={"textarea"}
                                rows={5}
                            />
                        </LableWrapper>

                        <LableWrapper label="Sku" name="sku">
                            <Input name="sku" register={register} />
                        </LableWrapper>

                        <LableWrapper label="images" name="images">
                            <InputImages {...register("images")} />
                        </LableWrapper>

                        <LableWrapper label="Guarantee" name="guarantee">
                            <Input name="guarantee" register={register} />
                        </LableWrapper>

                        <LableWrapper label="Price" name="price">
                            <Input
                                name="price"
                                register={register}
                                registerOptions={{
                                    valueAsNumber: true,
                                }}
                                as="input"
                                type="number"
                            />
                        </LableWrapper>

                        <LableWrapper name="brand" label="Brand">
                            <></>
                        </LableWrapper>

                        <LableWrapper label="Categories">
                            <Stack direction={"column"} spacing={2}>
                                <Autocomplete
                                    multiple
                                    id="tags-filled"
                                    options={["may khoan"]}
                                    defaultValue={[]}
                                    freeSolo
                                    renderTags={(
                                        value: readonly string[],
                                        getTagProps
                                    ) =>
                                        value.map(
                                            (option: string, index: number) => (
                                                <Chip
                                                    variant="outlined"
                                                    label={option}
                                                    {...getTagProps({ index })}
                                                />
                                            )
                                        )
                                    }
                                    renderInput={(params) => (
                                        <TextField
                                            {...params}
                                            variant="outlined"
                                            // label="freeSolo"
                                            placeholder="Favorites"
                                        />
                                    )}
                                />
                                <CustomizedTreeView />
                            </Stack>
                        </LableWrapper>
                    </div>
                </SimpleBarScroll>
                <Divider />
                <Stack
                    spacing={2}
                    direction={"row"}
                    alignItems={"center"}
                    height={"100px"}
                    mx={2}
                >
                    <Button
                        sx={{ flexGrow: 1 }}
                        variant="outlined"
                        onClick={handleClose}
                    >
                        Cancel
                    </Button>
                    <Button sx={{ flexGrow: 1 }} variant="contained">
                        Save
                    </Button>
                </Stack>
            </Stack>
        </form>
    );
};

export default CreateOrUpdateProduct;
