export interface CartItemUpdateOrCreateNew {
    productId: number;
    quantity: number;
}

export interface CartItemResponse {
    id: number;
    productId: number;
    quantity: number;
    createAt: string;
    updateAt: string;
}
