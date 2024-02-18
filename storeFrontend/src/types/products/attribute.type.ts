export interface ProductAtrributeResponse {
    id: number;
    name: string;
    isActive: boolean;
    items: ProductAttributeItem[];
}
export interface ProductAttributeItem {
    id: number;
    name: string;
    value: string;
}
