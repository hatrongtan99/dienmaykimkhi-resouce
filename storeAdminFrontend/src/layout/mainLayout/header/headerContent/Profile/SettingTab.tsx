import { useState } from "react";

// material-ui
import { useTheme } from "@mui/material/styles";
import {
    List,
    ListItemButton,
    ListItemIcon,
    ListItemText,
} from "@mui/material";

// assets
import { LockOutlined, QuestionCircleOutlined } from "@ant-design/icons";

const SettingTab = () => {
    const theme = useTheme();

    const [selectedIndex, setSelectedIndex] = useState(0);
    const handleListItemClick = (event: any, index: number) => {
        setSelectedIndex(index);
    };

    return (
        <List
            component="nav"
            sx={{
                p: 0,
                "& .MuiListItemIcon-root": {
                    minWidth: 32,
                    color: theme.palette.grey[500],
                },
            }}
        >
            <ListItemButton
                selected={selectedIndex === 0}
                onClick={(event) => handleListItemClick(event, 0)}
            >
                <ListItemIcon>
                    <QuestionCircleOutlined />
                </ListItemIcon>
                <ListItemText primary="Hỗ Trợ" />
            </ListItemButton>
            <ListItemButton
                selected={selectedIndex === 2}
                onClick={(event) => handleListItemClick(event, 2)}
            >
                <ListItemIcon>
                    <LockOutlined />
                </ListItemIcon>
                <ListItemText primary="Giới Thiệu" />
            </ListItemButton>
        </List>
    );
};

export default SettingTab;
