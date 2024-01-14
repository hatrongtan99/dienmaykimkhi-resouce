"use client"

import Link from "next/link"
import Button from "@/components/custom/button/Button"
import { AiFillCaretDown } from "react-icons/ai";
import AuthhHeader from "./AuthhHeader"
import CardHeaderIcon from "./CardHeaderIcon"
import SearchInput from "./SearchInput"
import { useEffect, useRef } from "react";

const HEADER_HEIGHT = 50

const Header = () => {
    const refHeaderRef = useRef<HTMLHeadElement>(null)

    useEffect(() => {
        const fixedHeader = () => {
            if (window.scrollY > HEADER_HEIGHT) {
                refHeaderRef.current!.classList.add("fixed")
                refHeaderRef.current!.classList.add("top-0")
                refHeaderRef.current!.classList.add("left-0")
            } else {
                refHeaderRef.current!.classList.remove("fixed")
                refHeaderRef.current!.classList.remove("top-0")
                refHeaderRef.current!.classList.remove("left-0")

            }
        }

        if (refHeaderRef.current) {
            window.addEventListener("scroll", fixedHeader)
        }

        return () => {
            window.removeEventListener("scroll", fixedHeader)
        }
    }, [])


    return (
        <header className="w-full bg-primary-color h-[var(--header-height)] z-50" ref={refHeaderRef}>
            <div className="container flex justify-between items-center h-full">
                <div className="text-xl text-white">
                    <Link href={"#"}>
                        dienmaykimkhi
                    </Link>
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
                <div className="">
                    <AuthhHeader />
                </div>
            </div>
        </header>
    )
}

export default Header