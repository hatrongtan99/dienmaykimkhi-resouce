"use client";

import PopoverWraper from "@/components/common/PopoverWraper";
import { AuthContext } from "@/context/AuthContextProvider";
import { UserAuthenticate } from "@/types/users/userInfo.type";
import Image from "next/image";
import Link from "next/link";
import { useContext, useEffect } from "react";
const DEFAULT_AVATAR = "/images/defaultAvatar.jpeg";

const UserAvatar = ({ auth }: { auth: UserAuthenticate }) => {
    const { setAuth } = useContext(AuthContext);
    useEffect(() => {
        if (auth) {
            setAuth(auth);
        }
    }, [auth]);
    return (
        <div className="ml-auto">
            <PopoverWraper
                stylePopContent="top-[100%] right-0 left-auto origin-[100px_top]"
                popOverContent={
                    <div className="my-2  w-[140px] text-sm">
                        <ul className="flex flex-col space-y-2">
                            <li>
                                <Link
                                    href={"/users/profile"}
                                    className="py-1 ml-2 hover:text-secondary-color block"
                                >
                                    Tài khoản của tôi
                                </Link>
                            </li>
                            <li>
                                <Link
                                    href={"/users/purchase"}
                                    className="py-1 ml-2 hover:text-secondary-color block"
                                >
                                    Đơn mua
                                </Link>
                            </li>
                            <li>
                                <Link
                                    href={""}
                                    className="py-1 ml-2 hover:text-secondary-color block"
                                >
                                    Đăng xuất
                                </Link>
                            </li>
                        </ul>
                    </div>
                }
            >
                <Link href={"/users/profile"} className="font-light">
                    <div className="flex text-white ">
                        <div className="mr-2 overflow-hidden rounded-1/2 ">
                            <Image
                                src={DEFAULT_AVATAR}
                                width={23}
                                height={23}
                                alt="avatar"
                            />
                        </div>
                        {auth.fullName}
                    </div>
                </Link>
            </PopoverWraper>
        </div>
    );
};

export default UserAvatar;
