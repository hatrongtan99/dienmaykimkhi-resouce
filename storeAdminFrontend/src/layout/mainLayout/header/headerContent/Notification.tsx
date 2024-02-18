import { useContext, useMemo, useRef, useState } from "react";

// material-ui
import { useTheme } from "@mui/material/styles";
import {
    Avatar,
    Badge,
    Box,
    ClickAwayListener,
    Divider,
    IconButton,
    List,
    ListItemButton,
    ListItemAvatar,
    ListItemText,
    ListItemSecondaryAction,
    Paper,
    Popper,
    Typography,
    useMediaQuery,
} from "@mui/material";

// project import
import MainCard from "@src/components/MainCard";
import Transitions from "@src/components/extends/Transitions";

// assets
import { BellOutlined, CloseOutlined, GiftOutlined } from "@ant-design/icons";
import SimpleBarScroll from "@src/third-party/SimpleBar";

// sx styles
const avatarSX = {
    width: 36,
    height: 36,
    fontSize: "1rem",
};

const actionSX = {
    mt: "6px",
    ml: 1,
    top: "auto",
    right: "auto",
    alignSelf: "flex-start",

    transform: "none",
};

const Notification = () => {
    const theme = useTheme();
    const matchesXs = useMediaQuery(theme.breakpoints.down("md"));

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

    const iconBackColorOpen = "grey.300";
    const iconBackColor = "grey.200";

    // const listNotiRender = useMemo(() => {
    //     return listNoti.map((noti) => {
    //         return (
    //             <Link
    //                 key={noti.id}
    //                 to={`/quan-ly/tu-dk/${noti.metadata.idTu}`}
    //                 onClick={handleToggle}
    //             >
    //                 <ListItemButton>
    //                     <ListItemAvatar>
    //                         <Avatar
    //                             sx={{
    //                                 color: `${noti.type}.main`,
    //                                 bgcolor: `${noti.type}.lighter`,
    //                             }}
    //                         >
    //                             <GiftOutlined />
    //                         </Avatar>
    //                     </ListItemAvatar>
    //                     <ListItemText
    //                         primary={
    //                             <Typography variant="h6">
    //                                 <Typography
    //                                     component="span"
    //                                     variant="subtitle1"
    //                                 >
    //                                     {noti.metadata.idTu}
    //                                 </Typography>{" "}
    //                             </Typography>
    //                         }
    //                         secondary={noti.message}
    //                     />
    //                     <ListItemSecondaryAction>
    //                         <Typography variant="caption" noWrap>
    //                             {moment(noti.timeAt).format("LT")}
    //                         </Typography>
    //                     </ListItemSecondaryAction>
    //                 </ListItemButton>
    //                 <Divider />
    //             </Link>
    //         );
    //     });
    // }, [convertToArrayNoti]);

    return (
        <Box sx={{ flexShrink: 0, ml: 0.75 }}>
            <IconButton
                disableRipple
                color="secondary"
                sx={{
                    color: "text.primary",
                    bgcolor: open ? iconBackColorOpen : iconBackColor,
                }}
                aria-label="open profile"
                ref={anchorRef}
                aria-controls={open ? "profile-grow" : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
                <Badge badgeContent={2} color="primary">
                    <BellOutlined />
                </Badge>
            </IconButton>
            <Popper
                placement={matchesXs ? "bottom" : "bottom-end"}
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
                                offset: [matchesXs ? -5 : 0, 9],
                            },
                        },
                    ],
                }}
            >
                {({ TransitionProps }) => (
                    <Transitions type="fade" in={open} {...TransitionProps}>
                        <Paper
                            sx={{
                                // boxShadow: theme.customShadows.z1,
                                width: "100%",
                                minWidth: 285,
                                maxWidth: 450,
                                [theme.breakpoints.down("md")]: {
                                    maxWidth: 285,
                                },
                            }}
                        >
                            <ClickAwayListener onClickAway={handleClose}>
                                <MainCard
                                    title="Thông Báo"
                                    elevation={0}
                                    content={false}
                                    secondary={
                                        <IconButton
                                            size="small"
                                            onClick={handleToggle}
                                            sx={{
                                                color: "text.primary",
                                                bgcolor: "grey.200",
                                            }}
                                        >
                                            <CloseOutlined />
                                        </IconButton>
                                    }
                                >
                                    <List
                                        component="nav"
                                        sx={{
                                            p: 0,
                                            "& .MuiListItemButton-root": {
                                                py: 0.5,
                                                "& .MuiAvatar-root": avatarSX,
                                                "& .MuiListItemSecondaryAction-root":
                                                    {
                                                        ...actionSX,
                                                        position: "relative",
                                                    },
                                            },
                                            height: "300px",
                                        }}
                                    >
                                        <SimpleBarScroll sx={{}}>
                                            <></>
                                        </SimpleBarScroll>
                                    </List>
                                    {/* <ListItemButton
                                        sx={{
                                            textAlign: "center",
                                            py: `${12}px !important`,
                                        }}
                                    >
                                        <ListItemText
                                            primary={
                                                <Typography
                                                    variant="h6"
                                                    color="primary"
                                                >
                                                    Xem Tất Cả
                                                </Typography>
                                            }
                                        />
                                    </ListItemButton> */}
                                </MainCard>
                            </ClickAwayListener>
                        </Paper>
                    </Transitions>
                )}
            </Popper>
        </Box>
    );
};

export default Notification;
