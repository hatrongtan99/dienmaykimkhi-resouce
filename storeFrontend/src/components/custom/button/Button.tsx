import { ComponentProps, ElementType, ReactNode } from 'react'
import { ClassNameValue, twMerge } from "tailwind-merge"

type ButtonProps<T extends ElementType> = {
    as?: T
    children: ReactNode
    size?: "sm" | "md" | "lg"
    variant?: "primary"
    | "secondary"
    | "text"
    | "primary-border"
    | "secondary-border";
    className?: ClassNameValue,
    disable?: boolean;
    leftIcon?: ReactNode;
    rightIcon?: ReactNode;
}

type MyButtonProps<T extends ElementType> = ButtonProps<T> & Omit<ComponentProps<T>, keyof ButtonProps<T>>

const Button = <T extends ElementType>({
    as,
    children,
    size = "md",
    variant = "primary",
    disable = false,
    leftIcon,
    rightIcon,
    className,
    ...props
}: MyButtonProps<T>) => {
    let Component = as ?? "button"

    if (disable) {
        Object.keys(props).forEach((key) => {
            // @ts-ignore
            if (key.startsWith("on") && typeof props[key] == "function") {
                // @ts-ignore
                delete props[key]
            }
        })
    }


    return (
        <Component
            className={twMerge("text-inherit capitalize inline-flex justify-center items-center outline-none px-4 py-1 rounded font-medium min-h-[36px] min-w-[100px] no-underline border space-x-2",
                // variant
                variant == "primary" && "text-white bg-primary-color hover:bg-primary-light-color",
                variant == "secondary" && "text-white bg-secondary-color hover:bg-secondary-light-color",
                variant == "primary-border" && "border-primary-color text-primary-color",
                variant == "secondary-border" && "bg-white border-secondary-light-color text-secondary-color hover:text-text-color",
                variant == "text" && "bg-inherit border-none text-primary-color hover:text-secondary-color",
                // size
                size == "sm" && "min-h-7 min-w-20 p-x-[10px] text-sm font-normal",
                size == "lg" && "px-6 min-w-[168px] min-h-[48px] text-lg",
                disable && "select-none border-[#999] bg-[#ccc] text-[#666] cursor-none hover:bg-[#ccc] hover:text-[#666]",
                className
            )} {...props}>
            {leftIcon && <>{leftIcon}</>}
            <span>{children}</span>
            {rightIcon && <>{rightIcon}</>}
        </Component>
    )
}

export default Button