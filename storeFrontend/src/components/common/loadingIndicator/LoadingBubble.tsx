import React from "react";
import DotBouce from "./DotBouce";
import { ClassNameValue, twMerge } from "tailwind-merge";

const LoadingBubble = ({ className }: { className?: ClassNameValue }) => {
    return (
        <div
            className={twMerge(
                "inline-flex space-x-2 justify-center items-center bg-[#e5e5e5] w-[52px] h-[40px] rounded-xl",
                className
            )}
        >
            <DotBouce className="animate-dot-bounce " />
            <DotBouce className="animate-dot-bounce animation-delay-150" />
            <DotBouce className="animate-dot-bounce animation-delay-300" />
        </div>
    );
};

export default LoadingBubble;
