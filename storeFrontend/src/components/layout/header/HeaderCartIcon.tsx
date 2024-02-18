"use client";
import { CartContext } from "@/context/CartContextProvider";
import Link from "next/link";
import { useContext } from "react";
import { BsFillCartCheckFill } from "react-icons/bs";

const HeaderCartIcon = () => {
    const { countCartItem } = useContext(CartContext);

    return (
        <div className="hover:cursor-pointer p-2 relative rounded-1/2 group">
            {/* cart icon */}
            <Link href={"/cart"}>
                <BsFillCartCheckFill
                    size={24}
                    color="#fff"
                    className="group-hover:scale-110 transition-transform ease-linear duration-100"
                />
                <div className="flex items-center justify-center absolute h-4 w-4 top-0 left-5 bg-white rounded-1/2 text-primary-color ring-[2px] ring-stone-500 ">
                    <span className="font-normal text-xs">{countCartItem}</span>
                </div>
            </Link>
        </div>
    );
};

export default HeaderCartIcon;
