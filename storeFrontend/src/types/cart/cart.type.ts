export interface CartItemUpdateOrCreateNew {
    productId: number;
    quantity: number;
}

export interface CartItemResponse {
    id: number;
    productId: number;
    slug: string;
    productName: string;
    discount: number;
    price: number;
    thumbnailUrl: string;
    quantity: number;
    createAt: string;
    updateAt: string;
}
