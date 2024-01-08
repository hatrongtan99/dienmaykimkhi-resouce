import { FieldFilterString } from "@/types";
import { useRouter, usePathname, useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";

import useBreakpoint from "./useBreakpoint";
export { useBreakpoint };

export const useDebounce = (text: string, delay: number = 300) => {
    return text;
};

export const useGetInitValueQueryString = (field: FieldFilterString) => {
    const searchParams = useSearchParams();

    return searchParams.get(field)?.split(",") ?? [];
};

export const usePushQueryUrl = (filed: FieldFilterString, value: string[]) => {
    const router = useRouter();
    const pathname = usePathname();
    const searchParams = useSearchParams();

    useEffect(() => {
        if (value.length == 0) {
        } else {
        }
    }, [filed, value]);
};
