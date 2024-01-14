"use client";
import Image from "next/image";
import { MouseEvent, forwardRef, memo } from "react";
import { BsCheckLg } from "react-icons/bs";
import { GrCheckbox } from "react-icons/gr";
import { twMerge } from "tailwind-merge";

interface CheckBoxFilterInputProps {
    label?: string;
    image?: string;
    active: boolean;
    handleClick?: (e: MouseEvent<HTMLElement>) => void;
}
const CheckBoxFilterInput = forwardRef<
    HTMLDivElement,
    CheckBoxFilterInputProps
>(
    (
        {
            label,
            image,
            active,
            handleClick,
            ...props
        }: CheckBoxFilterInputProps,
        ref
    ) => {
        return (
            <div
                className={twMerge(
                    "block py-1 pl-2 relative cursor-pointer text-secondary-color hover:text-secondary-light-color group",
                    active && "text-red-600"
                )}
                {...props}
                onClick={handleClick}
                ref={ref}
            >
                <div className="flex items-center">
                    <BsCheckLg
                        className={twMerge(
                            "absolute invisible",
                            active && "visible"
                        )}
                    />
                    <GrCheckbox className="shrink-0 text-text-color" />
                    {label ? (
                        <span className="text-sm font-normal ml-1">
                            {label}
                        </span>
                    ) : null}

                    {image ? (
                        <Image
                            src={image}
                            alt="Picture of the brand"
                            width={60}
                            height={45}
                            className="ml-2 group-hover:border px-1"
                        />
                    ) : null}
                </div>
            </div>
        );
    }
);

export default memo(CheckBoxFilterInput);
