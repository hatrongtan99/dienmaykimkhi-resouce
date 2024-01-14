import { FILED_FILTER_STRING } from "@/constants";
import { UndefinedInitialDataOptions } from "@tanstack/react-query";
export type FieldFilterString = (typeof FILED_FILTER_STRING)[number];

export type TypeButtonSlide = "prev" | "next";

export type MetadataPage = {
    _metadata: {
        page: number;
        pageSize: number;
        pageCount: number;
        totalCount: number;
    };
};

export type PageableParams = Partial<{
    page: number;
    limit: number;
}>;

export type CustomUndefinedInitialDataOptions<T> = Partial<
    Exclude<UndefinedInitialDataOptions<T>, "queryKey" | "queryFn">
>;
