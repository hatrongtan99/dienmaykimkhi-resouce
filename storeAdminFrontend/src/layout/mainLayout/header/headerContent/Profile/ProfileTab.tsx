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
import { EditOutlined, LogoutOutlined, UserOutlined } from "@ant-design/icons";

const ProfileTab = ({ handleLogout }: { handleLogout: () => void }) => {
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
                    <EditOutlined />
                </ListItemIcon>
                <ListItemText primary="Chỉnh Sửa Tài Khoản" />
            </ListItemButton>
            <ListItemButton
                selected={selectedIndex === 1}
                onClick={(event) => handleListItemClick(event, 1)}
            >
                <ListItemIcon>
                    <UserOutlined />
                </ListItemIcon>
                <ListItemText primary="Thông Tin Tài Khoản" />
            </ListItemButton>

            <ListItemButton
                selected={selectedIndex === 2}
                onClick={handleLogout}
            >
                <ListItemIcon>
                    <LogoutOutlined />
                </ListItemIcon>
                <ListItemText primary="Đăng Xuất" />
            </ListItemButton>
        </List>
    );
};
export default ProfileTab;
