import { Box, CircularProgress } from "@mui/material";
import { ComponentType, LazyExoticComponent, Suspense } from "react";

const Spinner = () => {
    return <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100%" }}>
        <CircularProgress />
    </Box>
}

const Loadable =
    <T extends ComponentType<any>>(Component: LazyExoticComponent<T>) =>
        (props: any) => {
            return (
                <Suspense fallback={<Spinner />} >
                    <Component {...props} />
                </Suspense>
            );
        };

export default Loadable;
