// project import
import Navigation from "./Navigation";
import SimpleBarScroll from "@src/third-party/SimpleBar";

const DrawerContent = () => (
    <SimpleBarScroll
        sx={{
            "& .simplebar-content": {
                display: "flex",
                flexDirection: "column",
            },
        }}
    >
        <Navigation />
    </SimpleBarScroll>
);

export default DrawerContent;
