import {
    Dispatch,
    ReactNode,
    SetStateAction,
    createContext,
    useState,
} from "react";

interface MenuContextProps {
    openDrawer: boolean;
    setOpenDrawer: Dispatch<SetStateAction<boolean>>;
}

export const MenuContext = createContext({} as MenuContextProps);

const MenuProvider = ({ children }: { children: ReactNode }) => {
    const [openDrawer, setOpenDrawer] = useState(true);
    return (
        <MenuContext.Provider value={{ openDrawer, setOpenDrawer }}>
            {children}
        </MenuContext.Provider>
    );
};

export default MenuProvider;
