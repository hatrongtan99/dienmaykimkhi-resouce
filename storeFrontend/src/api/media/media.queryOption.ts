import { QueryOptions, queryOptions } from "@tanstack/react-query";
import { getFileMediaById } from "./media.api";

export const getFileMediaByIdOption = ({
    id,
}: { id: number } & QueryOptions) => {
    return queryOptions({
        queryKey: ["get-file-media-by-id", { id }],
        queryFn: () => getFileMediaById({ id }),
    });
};
