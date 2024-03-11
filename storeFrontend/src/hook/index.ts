import { FieldFilterString } from "@/types";
import { usePathname, useSearchParams, useRouter } from "next/navigation";

import useBreakpoint from "./useBreakpoint";
import { useEffect } from "react";
export { useBreakpoint };

type Eventname = keyof GlobalEventHandlersEventMap;

export const useDebounce = (text: string, delay: number = 300) => {
    return text;
};

export const useAddEventListener = (
    eventName: Eventname,
    handler: (e: any) => void
) => {
    useEffect(() => {
        document.addEventListener(eventName, handler);

        return () => {
            document.removeEventListener(eventName, handler);
        };
    }, [eventName, handler]);
};
