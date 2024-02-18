import { ClassNameValue, twMerge } from "tailwind-merge";

const DotBouce = ({
    size = 4,
    className,
}: {
    size?: number;
    className?: ClassNameValue;
}) => {
    return (
        <div
            className={twMerge(
                "bg-[#57585a] rounded-full inline-block",
                className
            )}
            style={{ height: `${size}px`, width: `${size}px` }}
        ></div>
    );
};

export default DotBouce;
