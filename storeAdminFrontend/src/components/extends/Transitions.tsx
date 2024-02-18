import { ReactNode, forwardRef } from "react";

// material-ui
import { Fade, Box, Grow } from "@mui/material";

interface TransitionsProps {
    children: ReactNode;
    position?:
        | "top-right"
        | "top"
        | "bottom-left"
        | "bottom-right"
        | "bottom"
        | "top-left";
    type?: "grow" | "fade";
}

const Transitions = forwardRef(
    (
        {
            children,
            position = "top-left",
            type = "grow",
            ...props
        }: TransitionsProps,
        ref
    ) => {
        let positionSX = {
            transformOrigin: "0 0 0",
        };

        switch (position) {
            case "top-right":
            case "top":
            case "bottom-left":
            case "bottom-right":
            case "bottom":
            case "top-left":
            default:
                positionSX = {
                    transformOrigin: "0 0 0",
                };
                break;
        }

        return (
            <Box ref={ref}>
                {type === "grow" && (
                    <Grow {...props}>
                        <Box sx={positionSX}>{children}</Box>
                    </Grow>
                )}
                {type === "fade" && (
                    <Fade
                        {...props}
                        timeout={{
                            appear: 0,
                            enter: 300,
                            exit: 150,
                        }}
                    >
                        <Box sx={positionSX}>{children}</Box>
                    </Fade>
                )}
            </Box>
        );
    }
);

export default Transitions;
