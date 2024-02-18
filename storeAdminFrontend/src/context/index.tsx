import { ReactNode } from "react";
import MenuProvider from "./MenuProvider";

const ProviderContext = ({ children }: { children: ReactNode }) => {
    return <MenuProvider>{children}</MenuProvider>;
};

export default ProviderContext;
