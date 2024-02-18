import React, { HTMLAttributes, ReactNode } from "react";
import { ClassNameValue, twMerge } from "tailwind-merge";

const PopoverWraper = ({
    children,
    popOverContent,
    stylePopContent,
}: {
    children: ReactNode;
    popOverContent: ReactNode;
    stylePopContent?: HTMLAttributes<HTMLDivElement>["className"];
}) => {
    return (
        <div className="w-auto h-fit relative group">
            {children}
            <div
                className={twMerge(
                    "min-w-[100px] h-fit border rounded-sm absolute bg-white bottom-0 left-0 z-50 transition-all hidden animate-fadeOut group-hover:block group-hover:animate-fadeIn origin-[right_top]",
                    stylePopContent
                )}
                onAnimationEnd={(e) => {
                    (e.target as HTMLDivElement).classList.toggle("hidden");
                }}
            >
                {popOverContent}
            </div>
        </div>
    );
};

export default PopoverWraper;
