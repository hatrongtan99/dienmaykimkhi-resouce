import { MetadataPage } from "..";

export interface ProductDetailResponse {
    id: number;
    name: string;
    slug: string;
    images: number[];
    descriptionId: number;
    sku: string;
    guarantee: string;
    price: number;
    thumbnailId: number;
    brandId: number;
    categories: number[];
    productRelate: number[];
    metadata: MetadataProduct[];
    isAvailInStock: boolean;
    isActive: boolean;
}

interface MetadataProduct {
    id: number;
    name: string;
    value: string;
}

export interface ProductItemResponse {
    id: number;
    name: string;
    slug: string;
    price: number;
    thumbnail: string;

    brand: {
        id: number;
        name: string;
        slug: string;
        urlThumbnail: string | null;
    };
}

export interface ProductResponseWithPage extends MetadataPage {
    records: ProductItemResponse[];
}

export interface ProductLineCartDetail {
    id: number;
    name: string;
    slug: string;
    price: number;
    thumbnail: string;
}

export interface StatusProductInStock {
    productId: number;
    isInStock: boolean;
}
