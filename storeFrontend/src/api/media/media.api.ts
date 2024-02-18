import customFetch from "..";
import config from "@/constants";
import { MediaResponse } from "@/types/medias/medias.type";

const BASE_URL_MEDIA_API = config.BASE_URL_BFF + "/medias/medias";

export const getFileMediaById = ({ id }: { id: number }) => {
    return customFetch<MediaResponse>(BASE_URL_MEDIA_API + `/${id}`);
};
