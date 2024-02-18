import { QueryOptions, queryOptions } from "@tanstack/react-query";
import { getFileMediaById } from "./media.api";
import { CustomUndefinedInitialDataOptions } from "@/types";
import { MediaResponse } from "@/types/medias/medias.type";

export const getFileMediaByIdOption = ({
    id,
}: { id: number } & CustomUndefinedInitialDataOptions<MediaResponse>) => {
    return queryOptions({
        queryKey: ["get-file-media-by-id", { id }],
        queryFn: () => getFileMediaById({ id }),
    });
};
