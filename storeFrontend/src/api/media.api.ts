import { MediaResponse } from "@/types/medias.type";
import customFetch from ".";

const BASE_URL_MEDIA_API = "/api/medias";

export const getFileMediaById = ({ id }: { id: number }) => {
    return customFetch<MediaResponse>(BASE_URL_MEDIA_API + `/${id}`);
};
