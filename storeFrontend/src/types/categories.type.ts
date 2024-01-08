import { MetadataPage } from ".";

export interface CategoryResponse {
    id: number;
    name: string;
    slug: string;
    description: string | null;
    thumbnail: null;
    parentId: number | null;
    isActive: boolean;
}

export type CategoriesParentResponse = {
    records: CategoryResponse[];
} & MetadataPage;

export type CategoriesChildResponse = {
    records: CategoryResponse[];
} & MetadataPage;
