"use client"

import CheckBoxFilterInput from "@/components/filterProduct/CheckBoxFilterInput"
import { ClassNameValue, twMerge } from "tailwind-merge"


interface FilterSessionProps<T> {
    title: string,
    dataFilter: T[],
    className?: ClassNameValue,
    itemClassName?: ClassNameValue
}

const FilterSession = <T extends { id: number, image?: string, lable?: string }>({
    title,
    dataFilter,
    className,
    itemClassName
}: FilterSessionProps<T>) => {
    return (
        <section className="bg-white py-2 px-1 mt-1">
            <h4 className='mb-2 text-sm font-bold uppercase'>{title}</h4>

            <ul className={twMerge('flex flex-wrap', className)}>
                {dataFilter.map((item, index) => (
                    <li className={twMerge("w-1/2", itemClassName)} key={index}>
                        <CheckBoxFilterInput
                            active={false}
                            image={item.image}
                            data-id={item.id}
                            handleClick={() => { }}
                            label={item.lable}
                        />
                    </li>
                ))}
            </ul>
        </section>
    )
}

export default FilterSession