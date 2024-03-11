import { ComponentProps, ElementType } from "react";
import { FieldValues, RegisterOptions, UseFormRegister } from "react-hook-form";

interface InputProps<T extends ElementType> {
    name: string;
    register: UseFormRegister<FieldValues>;
    as?: T;
    registerOptions?: RegisterOptions<FieldValues, string>;
}

const Input = <T extends ElementType>({
    name,
    register,
    as,
    registerOptions,
    ...rest
}: InputProps<T> & Omit<ComponentProps<T>, keyof InputProps<T>>) => {
    let Component = as ?? "input";
    return (
        <Component
            id={name}
            className="w-full border rounded-sm px-4 py-3"
            {...register(name, registerOptions)}
            {...rest}
        />
    );
};

export default Input;
