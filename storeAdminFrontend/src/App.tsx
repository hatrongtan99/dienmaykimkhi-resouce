import { BrowserRouter } from "react-router-dom";
import ProviderContext from "./context";
import Routes from "./routes";
import { createTheme } from "@mui/material";
import ThemeProvider from "./themes";
import ScrollTop from "./components/ScrollTop";

function App() {
    const themes = createTheme();
    return (
        <BrowserRouter>
            <ProviderContext>
                <ThemeProvider>
                    <ScrollTop>
                        <Routes />
                    </ScrollTop>
                </ThemeProvider>
            </ProviderContext>
        </BrowserRouter>
    );
}

export default App;
