import { ReactElement } from "react";

const LableWrapper = ({
    label,
    children,
    name,
}: {
    label?: string;
    name?: string;
    children: ReactElement;
}) => {
    return (
        <div className="grid grid-cols-7 mb-2 items-center">
            {label ? (
                <div className="col-span-2">
                    <label htmlFor={name} className="text-sm font-medium">
                        {label}:
                    </label>
                </div>
            ) : null}
            <div className="col-span-5">{children}</div>
        </div>
    );
};

export default LableWrapper;
