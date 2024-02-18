"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import React, { ReactNode } from "react";
import { AiOutlineProfile, AiOutlineUser } from "react-icons/ai";

const body = [
    {
        label: "Tài Khoản Của Tôi",
        icon: <AiOutlineUser color="#225eb9" size={25} />,
        path: "/users/profile",
        children: [
            {
                label: "Hồ Sơ",
                path: "/users/profile",
            },
            { label: "Địa Chỉ", path: "/users/address" },
        ],
    },
    {
        label: "Đơn mua",
        icon: <AiOutlineProfile color="#225eb9" size={25} />,
        path: "/users/purchase",
    },
];

const UserLayout = ({ children }: { children: ReactNode }) => {
    const pathname = usePathname();
    return (
        <div className="container my-4">
            <div className="grid grid-cols-12 gap-4">
                <aside className="col-span-3 h-auto my-5 mx-4">
                    <div className="flex justify-center items-center py-2 border-b">
                        <div className={"overflow-hidden rounded-1/2 border"}>
                            <Image
                                alt="avatar"
                                src={""}
                                width={50}
                                height={50}
                            />
                        </div>
                        <p className="text-lg font-normal ml-2">hatrongtan</p>
                    </div>

                    <div className="flex flex-col space-y-3 ml-2 mt-2 text-sm font-medium">
                        {body.map((i, index) => {
                            const expandChild =
                                i.children &&
                                i.children.find((c) => c.path === pathname);
                            return (
                                <div
                                    className={`w-full ${
                                        pathname == i.path && !i.children
                                            ? "text-secondary-color"
                                            : ""
                                    }`}
                                    key={index}
                                >
                                    <Link
                                        className="flex items-center"
                                        href={i.path}
                                    >
                                        {i.icon}
                                        <span className="ml-2 ">{i.label}</span>
                                    </Link>

                                    {i.children ? (
                                        <div
                                            className={`ml-10 text-sm font-light text-text-light-color transition-all duration-600 ${
                                                expandChild
                                                    ? "min-h-[20px] h-fit opacity-100"
                                                    : "h-0 overflow-hidden min-h-0 opacity-0"
                                            }`}
                                        >
                                            {i.children.map((ic, index) => (
                                                <Link
                                                    href={ic.path}
                                                    className={`px-2 py-1 my-1 block ${
                                                        pathname == ic.path
                                                            ? "text-secondary-color"
                                                            : ""
                                                    }`}
                                                >
                                                    {ic.label}
                                                </Link>
                                            ))}
                                        </div>
                                    ) : null}
                                </div>
                            );
                        })}
                    </div>
                </aside>
                <div className={`col-span-9`}>{children}</div>
            </div>
        </div>
    );
};

export default UserLayout;
