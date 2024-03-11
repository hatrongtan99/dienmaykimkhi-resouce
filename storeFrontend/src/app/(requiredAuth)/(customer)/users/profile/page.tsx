"use client";

import { getDetailOrCreateUserOptions } from "@/api/user/userInfo/userInfo.queryOptions";
import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import Button from "@/components/custom/button/Button";
import { AuthContext } from "@/context/AuthContextProvider";
import { useQuery } from "@tanstack/react-query";
import Image from "next/image";
import { useContext } from "react";

const DEFAULT_AVATAR = "/images/defaultAvatar.jpeg";

const UserProfilePage = () => {
    const { auth } = useContext(AuthContext);
    const {
        data: userInfo,
        isError,
        isLoading,
    } = useQuery(
        getDetailOrCreateUserOptions({
            body: {
                fullName: auth!.fullName,
                avatarUrl: null,
                numberPhone: null,
                email: auth!.email,
            },
        })
    );

    if (isError) {
        throw new Error("some thing wrong!");
    }
    if (isLoading) {
        return (
            <div className="w-full min-h-[400px] bg-white flex items-center justify-center rounded-md">
                <LoadingBubble />
            </div>
        );
    }
    if (userInfo) {
        return (
            <div className="py-6 px-8 bg-white w-full min-h-[400px] rounded-md">
                <div className="pb-3 border-b">
                    <h3 className="text-lg font-semibold">Hồ Sơ Của Tôi</h3>
                    <p className="text-sm font-light">
                        Quản lý thông tin hồ sơ để bảo mật tài khoản
                    </p>
                </div>

                <form>
                    <div className="flex space-x-5 mt-2">
                        <table className="w-[600px]">
                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Tên đăng nhập
                                </td>
                                <td className="w-auto pl-4 py-3 text-sm">
                                    {auth?.username}
                                </td>
                            </tr>

                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Tên người dùng
                                </td>
                                <td className="w-auto pl-4 py-3 text-sm">
                                    <input
                                        type="text"
                                        className="outline-none w-full h-full border px-4 py-2"
                                        value={userInfo.fullName}
                                    />
                                </td>
                            </tr>

                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Email
                                </td>
                                <td className="w-auto pl-4 py-3 text-sm">
                                    <input
                                        type="text"
                                        className="outline-none w-full h-full border px-4 py-2"
                                        value={userInfo.email}
                                    />
                                </td>
                            </tr>

                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Số Điện Thoại
                                </td>
                                <td className="w-auto pl-4 py-3 text-sm">
                                    <input
                                        type="text"
                                        className="outline-none w-full h-full border px-4 py-2"
                                        value={userInfo.numberPhone}
                                    />
                                </td>
                            </tr>

                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Giới Tính
                                </td>
                                <td className="w-auto pl-4 py-3 text-sm">
                                    <div className="flex space-x-6">
                                        <div className="flex items-center">
                                            <input
                                                type="radio"
                                                id="male"
                                                name="sex"
                                            />
                                            <label
                                                className="ml-2"
                                                htmlFor="male"
                                            >
                                                Nam
                                            </label>
                                        </div>
                                        <div className="flex items-center">
                                            <input
                                                type="radio"
                                                id="female"
                                                name="sex"
                                            />
                                            <label
                                                className="ml-2"
                                                htmlFor="female"
                                            >
                                                Nữ
                                            </label>
                                        </div>
                                        <div className="flex items-center">
                                            <input
                                                type="radio"
                                                id="orther"
                                                name="sex"
                                            />
                                            <label
                                                className="ml-2"
                                                htmlFor="orther"
                                            >
                                                Khác
                                            </label>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td className="min-w-[20%] text-text-light-color text-right text-sm">
                                    Ngày Sinh
                                </td>

                                <td className="w-auto pl-4 py-3 text-sm">
                                    <input
                                        type="date"
                                        className="outline-none w-full h-full border px-4 py-2"
                                    />
                                </td>
                            </tr>
                        </table>
                        <div className="grow self-center">
                            <div className="flex flex-col items-center border-l h-[200px] justify-around">
                                <div className="rounded-1/2 overflow-hidden">
                                    <Image
                                        width={100}
                                        height={100}
                                        alt="avatar"
                                        src={
                                            userInfo.avatarUrl || DEFAULT_AVATAR
                                        }
                                    />
                                </div>
                                <div>
                                    <Button
                                        as="button"
                                        variant="secondary-border"
                                    >
                                        Chọn Ảnh
                                    </Button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="ml-[100px] mt-4">
                        <Button as="button">Lưu</Button>
                    </div>
                </form>
            </div>
        );
    }
};

export default UserProfilePage;
