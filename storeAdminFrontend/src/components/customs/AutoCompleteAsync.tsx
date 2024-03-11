import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import CircularProgress from "@mui/material/CircularProgress";
import { SxProps } from "@mui/material";
import { Theme } from "@mui/system";

interface AutocompleteAsyncProps {
    options?: { title: string; id: number | string }[];
    isLoading: boolean;
    label: string;
    sx?: SxProps<Theme>;
}

export default function AutocompleteAsync({
    options = [],
    isLoading,
    label,
    sx,
    ...props
}: AutocompleteAsyncProps) {
    return (
        <Autocomplete
            {...props}
            size="small"
            id="asynchronous-demo"
            sx={{ width: 300, ...sx }}
            isOptionEqualToValue={(option, value) =>
                option.title === value.title
            }
            getOptionLabel={(option) => option.title}
            options={options}
            loading={isLoading}
            loadingText="is loading..."
            renderInput={(params) => (
                <TextField
                    {...params}
                    label={label}
                    InputProps={{
                        ...params.InputProps,
                        endAdornment: (
                            <React.Fragment>
                                {isLoading ? (
                                    <CircularProgress
                                        color="inherit"
                                        size={20}
                                    />
                                ) : null}
                                {params.InputProps.endAdornment}
                            </React.Fragment>
                        ),
                    }}
                />
            )}
        />
    );
}
