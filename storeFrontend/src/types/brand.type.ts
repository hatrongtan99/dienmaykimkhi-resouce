export interface BrandResponse {
    id: number;
    name: string;
    thumbnail: {
        id: number;
        url: string;
    };
    slug: string;
    isActive: boolean;
}
