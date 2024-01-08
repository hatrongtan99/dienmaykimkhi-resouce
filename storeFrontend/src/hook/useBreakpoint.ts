import resouleTailwindCss from "tailwindcss/resolveConfig";
import tailwindConfig from "../../tailwind.config";
import { useMediaQuery } from "react-responsive";

const allConfigTailwindCss = resouleTailwindCss(tailwindConfig);
type KeyBreakpoint = "sm" | "md" | "lg" | "xl" | "2xl";

type Breakpoint = {
    [key in KeyBreakpoint]: string;
};

const useBreakpoint = (key: KeyBreakpoint) => {
    const screensConfig = allConfigTailwindCss.theme?.screens as Breakpoint;
    const bool = useMediaQuery({
        query: `(min-width: ${screensConfig[key]})`,
    });
    return bool;
};

export default useBreakpoint;
