import { Box, FormControl, InputAdornment, OutlinedInput } from "@mui/material";

import { SearchOutlined } from "@ant-design/icons";

const Search = () => (
    <Box sx={{ width: "100%", ml: { xs: 0, md: 1 } }}>
        <FormControl sx={{ width: { xs: "100%", md: 250 } }}>
            <OutlinedInput
                size="small"
                id="header-search"
                startAdornment={
                    <InputAdornment
                        position="start"
                        sx={{ mr: -0.5, color: "text.secondary" }}
                    >
                        <SearchOutlined />
                    </InputAdornment>
                }
                inputProps={{
                    "aria-label": "weight",
                }}
                placeholder="Search"
            />
        </FormControl>
    </Box>
);

export default Search;
