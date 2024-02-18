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
        useState<SearchParamsObjectType>(() => {
            const result: any = {};
            const iterator = searchParams.entries();
            let current = iterator.next();
            while (!current.done) {
                const [key, value] = current.value;
                result[key] = value.split(",");
                current = iterator.next();
            }
            return result;
        });

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
    }, []);

    // reset if path route change
    useEffect(() => {
        if (firstLoadRef.current) {
            setSearchParamsObject({});
        }
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
