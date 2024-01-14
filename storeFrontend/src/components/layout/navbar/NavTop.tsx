"use client"
import Button from '@/components/custom/button/Button'
import MenuCategories from '@/components/menu/MenuCategories';
import Link from 'next/link';
import { AiFillCaretDown } from 'react-icons/ai';
import { IoMenuOutline } from "react-icons/io5";
import { usePathname, useSelectedLayoutSegment, useSelectedLayoutSegments } from 'next/navigation'
import { useEffect, useRef } from 'react';

const navList = [
    {
        lable: "Giới thiệu",
        path: "/"
    },
    {
        lable: "Khuyến mãi",
        path: "/"
    },
    {
        lable: "Tư vấn tiêu dùng",
        path: "/"
    },
    {
        lable: "Liên hệ báo giá",
        path: "/"
    },
    {
        lable: "Khoan Pin",
        path: "/"
    },
    {
        lable: "Thiết bị đo",
        path: "/"
    }

]

const NavTop = () => {

    const pathname = usePathname()
    const isHomePage = pathname === "/";

    const refHeaderRef = useRef<HTMLElement>(null)

    useEffect(() => {
        const addMargin = () => {
            if (window.scrollY > 50 - 5) {
                refHeaderRef.current!.classList.add("mt-[50px]")
            } else {
                refHeaderRef.current!.classList.remove("mt-[50px]")
            }
        }

        if (refHeaderRef.current) {
            window.addEventListener("scroll", addMargin)
        }

        return () => {
            window.removeEventListener("scroll", addMargin)
        }
    }, [])



    return (
        <nav className="shadow-md" ref={refHeaderRef}>
            <div className="container">
                <ul className='flex items-center py-1'>
                    <li className='w-56 border-r  relative group'>
                        <Button
                            variant="text"
                            leftIcon={<IoMenuOutline size={16} className='text-gray-400' />}
                            rightIcon={<AiFillCaretDown size={16} className='text-gray-400 !ml-7' />}
                            className="text-text-color text-sm hover:text-text-color px-2 w-full font-normal"
                        >
                            Danh mục sản phẩm
                        </Button>
                        <MenuCategories className={`group-hover:visible invisible absolute ${isHomePage && "visible"}`} />
                    </li>
                    {navList.map((item) => (
                        <li key={item.lable} className="px-2 relative after:contents-[''] after:h-5 after:absolute after:top-1/2 after:-translate-y-1/2 after:right-0 after:border-r after: last:after:hidden">
                            <Button as={Link} href={item.path} variant='text' className="text-sm font-normal text-text-color min-w-0">
                                {item.lable}
                            </Button>
                        </li>
                    ))}
                </ul>
            </div>
        </nav>
    );
}

export default NavTop