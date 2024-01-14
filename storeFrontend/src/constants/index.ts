export const SORT_QUERY_PRICE_ASC = "price_asc";
export const SORT_QUERY_PRICE_DESC = "price_desc";
export const FILED_FILTER_STRING = [
    "sort",
    "price",
    "filter",
    "brand",
] as const;

export default {
    BASE_URL_BFF: process.env.NEXT_PUBLIC_BASE_URL_API,
};
