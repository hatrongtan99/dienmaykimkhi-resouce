"use client";
import { FieldFilterString, PageableParams } from "@/types";
import { buildParamsString } from "@/utils";
import { usePathname, useRouter, useSearchParams } from "next/navigation";
import {
    Dispatch,
    ReactNode,
    SetStateAction,
    createContext,
    useEffect,
    useRef,
    useState,
} from "react";

export type SearchParamsObjectType = Partial<{
    [key in FieldFilterString]: string[];
}> &
    PageableParams;

export const RouterConftext = createContext(
    {} as {
        // searchParamsObject: SearchParamsObjectType;
        setSearchParamsObject: Dispatch<SetStateAction<SearchParamsObjectType>>;
    }
);

const RouterConftextProvider = ({ children }: { children: ReactNode }) => {
    const pathname = usePathname();
    const searchParams = useSearchParams();
    const router = useRouter();

    const [searchParamsObject, setSearchParamsObject] =
        useState<SearchParamsObjectType>({});
    const firstLoadRef = useRef(false);

    useEffect(() => {
        const result: { [key: string]: string } = {};
        for (const [key, value] of Object.entries(searchParamsObject)) {
            let stringParams = "";
            if (Array.isArray(value)) {
                stringParams = value.join(",");
            } else {
                stringParams = value.toString();
            }
            result[key] = stringParams;
        }
        if (firstLoadRef.current) {
            router.push(
                pathname +
                    "?" +
                    buildParamsString(result, searchParams.toString()),
                {
                    scroll: false,
                }
            );
        }
    }, [router, pathname, searchParamsObject]);

    useEffect(() => {
        firstLoadRef.current = true;
        return () => {
            firstLoadRef.current = false;
            // reset when change pathname
            setSearchParamsObject({});
        };
    }, [pathname]);

    return (
        <RouterConftext.Provider
            value={{
                setSearchParamsObject,
            }}
        >
            {children}
        </RouterConftext.Provider>
    );
};

export default RouterConftextProvider;
