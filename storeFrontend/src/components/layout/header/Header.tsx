"use client";

import Link from "next/link";
import Button from "@/components/custom/button/Button";
import { AiFillCaretDown } from "react-icons/ai";
import CardHeaderIcon from "./HeaderCartIcon";
import SearchInput from "./SearchInput";
import { ReactNode, useEffect, useRef } from "react";

const HEADER_HEIGHT = 50;

const Header = ({ children }: { children: ReactNode }) => {
    const refHeaderRef = useRef<HTMLHeadElement>(null);

    useEffect(() => {
        const fixedHeader = () => {
            if (window.scrollY > HEADER_HEIGHT) {
                refHeaderRef.current!.classList.add("fixed", "top-0", "left-0");
            } else {
                refHeaderRef.current!.classList.remove(
                    "fixed",
                    "top-0",
                    "left-0"
                );
            }
        };

        if (refHeaderRef.current) {
            window.addEventListener("scroll", fixedHeader);
        }

        return () => {
            window.removeEventListener("scroll", fixedHeader);
        };
    }, []);

    return (
        <header
            className="w-full bg-primary-color h-[var(--header-height)] z-50"
            ref={refHeaderRef}
        >
            <div className="container flex justify-between items-center h-full">
                <div className="text-xl text-white">
                    <Link href={"#"}>dienmaykimkhi</Link>
                </div>
                {/* search */}
                <div className="">
                    <SearchInput />
                </div>

                {/* cart */}
                <div>
                    <CardHeaderIcon />
                </div>

                {/*  button product seen */}
                <div>
                    <Button
                        variant="secondary-border"
                        size="sm"
                        rightIcon={<AiFillCaretDown />}
                    >
                        Sản phẩm đã xem
                    </Button>
                </div>
                <div className="">{children}</div>
            </div>
        </header>
    );
};

export default Header;
