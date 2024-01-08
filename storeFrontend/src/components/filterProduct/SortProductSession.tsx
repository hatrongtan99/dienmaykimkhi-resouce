"use client"

import CheckBoxFilterInput from "./CheckBoxFilterInput"
import { SORT_QUERY_PRICE_ASC, SORT_QUERY_PRICE_DESC } from "@/constants"
import { MouseEvent, useState } from "react";
import { useGetInitValueQueryString, usePushQueryUrl } from "@/hook";

const SortProductSession = () => {
    const [sortValue, setSortValue] = useState(useGetInitValueQueryString("sort"))

    const handleCheckBox = (e: MouseEvent<HTMLElement>) => {

    }

    usePushQueryUrl("sort", sortValue)

    return (
        <div className="flex justify-end items-center text-sm py-2 bg-white border-b  pr-2">
            <h6 className="font-normal">Sắp xếp: </h6>
            <div className="inline mr-2">
                <CheckBoxFilterInput
                    active={sortValue.includes(SORT_QUERY_PRICE_ASC)}
                    label="Giá thấp đến cao"
                    data-sort={SORT_QUERY_PRICE_ASC}
                    handleClick={handleCheckBox}
                />
            </div>

            <div className="inline mr-2">
                <CheckBoxFilterInput
                    active={sortValue.includes(SORT_QUERY_PRICE_DESC)}
                    label="Giá cao đến thấp"
                    data-sort={SORT_QUERY_PRICE_DESC}
                    handleClick={handleCheckBox}
                />
            </div>

        </div>
    )
}

export default SortProductSession

