"use client";

import { RouterConftext } from "@/api/context/RouterContextProvider";
import CheckBoxFilterInput from "@/components/filterProduct/CheckBoxFilterInput";
import { FieldFilterString } from "@/types";
import { useSearchParams } from "next/navigation";
import { MouseEvent, useContext, useEffect, useRef } from "react";
import { ClassNameValue, twMerge } from "tailwind-merge";

interface FilterSessionProps<T> {
    title: string;
    dataFilter: T[];
    className?: ClassNameValue;
    itemClassName?: ClassNameValue;
    fieldFilter: FieldFilterString;
}

const FilterSession = <
    T extends { id: number; image?: string; lable?: string; dataSet: string }
>({
    title,
    dataFilter,
    className,
    itemClassName,
    fieldFilter,
}: FilterSessionProps<T>) => {
    const { setSearchParamsObject } = useContext(RouterConftext);

    const searchParams = useSearchParams();

    const refCheckBox = useRef<HTMLDivElement[]>([]);

    useEffect(() => {
        dataFilter.forEach((item, index) => {
            refCheckBox.current[index].setAttribute(
                `data-${fieldFilter}`,
                item.dataSet
            );
        });
    }, [fieldFilter]);

    const handleClickCheckbox = (e: MouseEvent<HTMLElement>) => {
        const datasetValue = e.currentTarget.dataset[`${fieldFilter}`];
        if (datasetValue) {
            setSearchParamsObject((prev) => {
                const result = { ...prev };
                const listValueSet = prev[fieldFilter];
                if (listValueSet) {
                    const existInField = listValueSet.includes(datasetValue);
                    if (existInField) {
                        result[fieldFilter] = listValueSet.filter(
                            (i) => i != datasetValue
                        );
                    } else {
                        result[fieldFilter] = [...listValueSet, datasetValue];
                    }
                } else {
                    result[fieldFilter] = [datasetValue];
                }
                return result;
            });
        }
    };

    return (
        <section className="bg-white py-2 px-1 mt-1">
            <h4 className="mb-2 text-sm font-bold uppercase">{title}</h4>

            <ul className={twMerge("flex flex-wrap", className)}>
                {dataFilter.map((item, index) => (
                    <li className={twMerge("w-1/2", itemClassName)} key={index}>
                        <CheckBoxFilterInput
                            active={
                                !!searchParams
                                    .get(fieldFilter)
                                    ?.split(",")
                                    ?.includes(item.dataSet)
                            }
                            image={item.image}
                            handleClick={handleClickCheckbox}
                            label={item.lable}
                            ref={(el) =>
                                el && (refCheckBox.current[index] = el)
                            }
                        />
                    </li>
                ))}
            </ul>
        </section>
    );
};

export default FilterSession;
