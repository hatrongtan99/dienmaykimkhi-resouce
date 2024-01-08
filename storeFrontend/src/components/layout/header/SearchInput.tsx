"use client"

import { useDebounce } from "@/hook"
import { useState } from "react"
import { BsSearch } from "react-icons/bs"

const SearchInput = () => {

    const [textSearch, setTetxSearch] = useState("");
    const valueSeach = useDebounce(textSearch);
    const isLoading = false
    return (
        <>
            <div className="flex w-full rounded-md bg-white py-1.5 text-sm font-light overflow-hidden">
                <input type="text" className="outline-none border-none bg-transparent text-text-color flex-grow pl-4" />
                <button className="bg-transparent text-text-color px-3">
                    <BsSearch size={16} />
                </button>
            </div>
            {/* {!!textSearch && (
                <div className="absolute h-24 w-24 border border-solid  bg-white z-10">
                    {isLoading ? <div className="flex content-center items-center">loadding...</div> : <div>data is show</div>}
                </div>
            )} */}
        </>
    )
}

export default SearchInput