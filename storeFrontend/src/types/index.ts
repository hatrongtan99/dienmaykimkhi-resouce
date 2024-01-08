export type FieldFilterString = "sort" | "price" | "filter" | "brand";

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
