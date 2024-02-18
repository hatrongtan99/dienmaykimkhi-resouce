import { ReactNode, useContext, useRef, useState } from "react";

// material-ui
import { useTheme } from "@mui/material/styles";
import {
    Avatar,
    Box,
    ButtonBase,
    CardContent,
    ClickAwayListener,
    Grid,
    IconButton,
    Paper,
    Popper,
    Stack,
    Tab,
    Tabs,
    Typography,
} from "@mui/material";

// project import
import MainCard from "@src/components/MainCard";
import ProfileTab from "./ProfileTab";
import SettingTab from "./SettingTab";

// assets
import defaultAvatar from "@src/assets/avatar/defaultAvatar.jpeg";

import {
    LogoutOutlined,
    SettingOutlined,
    UserOutlined,
} from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import Transitions from "@src/components/extends/Transitions";

// tab panel wrapper
function TabPanel({
    children,
    value,
    index,
    ...other
}: {
    children: ReactNode;
    value: any;
    index: any;
}) {
    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`profile-tabpanel-${index}`}
            aria-labelledby={`profile-tab-${index}`}
            {...other}
        >
            {value === index && children}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `profile-tab-${index}`,
        "aria-controls": `profile-tabpanel-${index}`,
    };
}

const Profile = () => {
    const theme = useTheme();

    const navigate = useNavigate();

    const anchorRef = useRef<HTMLButtonElement>(null);
    const [open, setOpen] = useState(false);
    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event: any) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }
        setOpen(false);
    };

    const [value, setValue] = useState(0);

    const handleChange = (event: any, newValue: any) => {
        setValue(newValue);
    };

    const iconBackColorOpen = "grey.300";

    return (
        <Box sx={{ flexShrink: 0, ml: 0.75 }}>
            <ButtonBase
                sx={{
                    p: 0.25,
                    bgcolor: open ? iconBackColorOpen : "transparent",
                    borderRadius: 1,
                    "&:hover": { bgcolor: "secondary.lighter" },
                }}
                aria-label="open profile"
                ref={anchorRef}
                aria-controls={open ? "profile-grow" : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
                <Stack
                    direction="row"
                    spacing={2}
                    alignItems="center"
                    sx={{ p: 0.5 }}
                >
                    <Avatar
                        alt="profile user"
                        src={defaultAvatar}
                        sx={{ width: 32, height: 32 }}
                    />
                    <Typography variant="subtitle1">admin</Typography>
                </Stack>
            </ButtonBase>
            <Popper
                placement="bottom-end"
                open={open}
                anchorEl={anchorRef.current}
                role={undefined}
                transition
                disablePortal
                popperOptions={{
                    modifiers: [
                        {
                            name: "offset",
                            options: {
                                offset: [0, 9],
                            },
                        },
                    ],
                }}
            >
                {({ TransitionProps }) => (
                    <Transitions type="fade" in={open} {...TransitionProps}>
                        {open && (
                            <Paper
                                sx={{
                                    // boxShadow: theme.customShadows.z1,
                                    width: 290,
                                    minWidth: 240,
                                    maxWidth: 290,
                                    [theme.breakpoints.down("md")]: {
                                        maxWidth: 250,
                                    },
                                }}
                            >
                                <ClickAwayListener onClickAway={handleClose}>
                                    <MainCard
                                        elevation={0}
                                        border={false}
                                        content={false}
                                    >
                                        <CardContent sx={{ px: 2.5, pt: 3 }}>
                                            <Grid
                                                container
                                                justifyContent="space-between"
                                                alignItems="center"
                                            >
                                                <Grid item>
                                                    <Stack
                                                        direction="row"
                                                        spacing={1.25}
                                                        alignItems="center"
                                                    >
                                                        <Avatar
                                                            alt="profile user"
                                                            src={defaultAvatar}
                                                            sx={{
                                                                width: 32,
                                                                height: 32,
                                                            }}
                                                        />
                                                        <Stack>
                                                            <Typography variant="h6">
                                                                admin
                                                            </Typography>
                                                            <Typography
                                                                variant="body2"
                                                                color="textSecondary"
                                                            >
                                                                admin@gmail.com
                                                            </Typography>
                                                        </Stack>
                                                    </Stack>
                                                </Grid>
                                                <Grid item>
                                                    <IconButton
                                                        size="large"
                                                        color="secondary"
                                                        onClick={() => {}}
                                                    >
                                                        <LogoutOutlined />
                                                    </IconButton>
                                                </Grid>
                                            </Grid>
                                        </CardContent>
                                        <>
                                            <Box
                                                sx={{
                                                    borderBottom: 1,
                                                    borderColor: "divider",
                                                }}
                                            >
                                                <Tabs
                                                    variant="fullWidth"
                                                    value={value}
                                                    onChange={handleChange}
                                                    aria-label="profile tabs"
                                                >
                                                    <Tab
                                                        sx={{
                                                            display: "flex",
                                                            flexDirection:
                                                                "row",
                                                            justifyContent:
                                                                "center",
                                                            alignItems:
                                                                "center",
                                                            textTransform:
                                                                "capitalize",
                                                        }}
                                                        icon={
                                                            <UserOutlined
                                                                style={{
                                                                    marginBottom: 0,
                                                                    marginRight:
                                                                        "10px",
                                                                }}
                                                            />
                                                        }
                                                        label="Tài Khoản"
                                                        {...a11yProps(0)}
                                                    />
                                                    <Tab
                                                        sx={{
                                                            display: "flex",
                                                            flexDirection:
                                                                "row",
                                                            justifyContent:
                                                                "center",
                                                            alignItems:
                                                                "center",
                                                            textTransform:
                                                                "capitalize",
                                                        }}
                                                        icon={
                                                            <SettingOutlined
                                                                style={{
                                                                    marginBottom: 0,
                                                                    marginRight:
                                                                        "10px",
                                                                }}
                                                            />
                                                        }
                                                        label="Cài Đặt"
                                                        {...a11yProps(1)}
                                                    />
                                                </Tabs>
                                            </Box>
                                            <TabPanel value={value} index={0}>
                                                <ProfileTab
                                                    handleLogout={() => {}}
                                                />
                                            </TabPanel>
                                            <TabPanel value={value} index={1}>
                                                <SettingTab />
                                            </TabPanel>
                                        </>
                                    </MainCard>
                                </ClickAwayListener>
                            </Paper>
                        )}
                    </Transitions>
                )}
            </Popper>
        </Box>
    );
};

export default Profile;
