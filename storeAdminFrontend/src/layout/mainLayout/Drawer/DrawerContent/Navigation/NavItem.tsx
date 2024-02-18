import { forwardRef, useContext, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";

// material-ui
import { useTheme } from "@mui/material/styles";
import {
    Avatar,
    Chip,
    ListItemButton,
    ListItemIcon,
    ListItemText,
    Tooltip,
    Typography,
} from "@mui/material";
import { MenuContext } from "@src/context/MenuProvider";
import { ExpandLess, ExpandMore } from "@mui/icons-material";
import { MenuItems } from ".";

const NavItem = ({
    item,
    level,
    openChild,
    setOpenChild,
    collapse = false,
}: {
    item: MenuItems;
    level: number;
    openChild?: boolean;
    setOpenChild?: () => void;
    collapse?: boolean;
}) => {
    const theme = useTheme();
    const { pathname } = useLocation();

    const { openDrawer } = useContext(MenuContext);

    let itemTarget = "_self";
    if (item.target) {
        itemTarget = "_blank";
    }

    let listItemProps = {
        component: forwardRef((props, ref: any) => (
            <Link ref={ref} {...props} to={item.url!} target={itemTarget} />
        )),
    };

    // if (item?.external) {
    //     listItemProps = { component: "a", href: item.url, target: itemTarget };
    // }

    const Icon = item.icon;
    const itemIcon = item.icon ? (
        <Icon style={{ fontSize: openDrawer ? "1rem" : "1.25rem" }} />
    ) : (
        false
    );

    const isSelected = pathname.includes(item.url!);

    const textColor = "text.primary";
    const iconSelectedColor = "primary.main";

    return (
        <ListItemButton
            onClick={() => {
                collapse && setOpenChild!();
            }}
            {...listItemProps}
            disabled={item.disabled}
            selected={isSelected}
            sx={{
                zIndex: 1201,
                pl: openDrawer ? `${level * 20}px` : 1.5,
                py: !openDrawer && level === 1 ? 1.25 : 1,
                ...(openDrawer && {
                    "&:hover": {
                        bgcolor: "primary.lighter",
                    },
                    "&.Mui-selected": {
                        bgcolor: "primary.lighter",
                        borderRight: `2px solid ${theme.palette.primary.main}`,
                        color: iconSelectedColor,
                        "&:hover": {
                            color: iconSelectedColor,
                            bgcolor: "primary.lighter",
                        },
                    },
                }),
                ...(!openDrawer
                    ? {
                          "&:hover": {
                              bgcolor: "transparent",
                          },
                          "&.Mui-selected": {
                              "&:hover": {
                                  bgcolor: "transparent",
                              },
                              bgcolor: "transparent",
                          },
                      }
                    : {}),
            }}
        >
            {itemIcon && (
                <Tooltip
                    title={item.title}
                    arrow
                    placement="right"
                    enterDelay={0}
                    leaveDelay={200}
                >
                    <ListItemIcon
                        sx={{
                            minWidth: 28,
                            color: isSelected ? iconSelectedColor : textColor,
                            ...(!openDrawer
                                ? {
                                      borderRadius: 1.5,
                                      width: 36,
                                      height: 36,
                                      alignItems: "center",
                                      justifyContent: "center",
                                      "&:hover": {
                                          bgcolor: "secondary.lighter",
                                      },
                                  }
                                : {}),
                            ...(!openDrawer
                                ? isSelected && {
                                      bgcolor: "primary.lighter",
                                      "&:hover": {
                                          bgcolor: "primary.lighter",
                                      },
                                  }
                                : {}),
                        }}
                    >
                        {itemIcon}
                    </ListItemIcon>
                </Tooltip>
            )}
            {(openDrawer || (!openDrawer && level != 1 && level != 2)) && (
                <ListItemText
                    primary={
                        <Typography
                            variant="h6"
                            sx={{
                                color: isSelected
                                    ? iconSelectedColor
                                    : textColor,
                            }}
                        >
                            {item.title}
                        </Typography>
                    }
                />
            )}

            {/* {(openDrawer || (!openDrawer && level !== 1)) && item.chip && (
                <Chip
                    color={item.chip.color}
                    variant={item.chip.variant}
                    size={item.chip.size}
                    label={item.chip.label}
                    avatar={
                        item.chip.avatar && <Avatar>{item.chip.avatar}</Avatar>
                    }
                />
            )} */}
            {collapse &&
                openDrawer &&
                (openChild ? <ExpandLess /> : <ExpandMore />)}
        </ListItemButton>
    );
};

export default NavItem;
