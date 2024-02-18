"use client";

import BottomCart from "@/components/cart/BottomCart";
import CartItem from "@/components/cart/CartItem";
import HeadCart from "@/components/cart/HeadCart";
import { CartContext } from "@/context/CartContextProvider";
import { useContext } from "react";

const CartPage = () => {
    const { listDetailCartItem } = useContext(CartContext);

    return (
        <section className="container">
            <HeadCart />
            {listDetailCartItem?.map((cartItem) => (
                <CartItem key={cartItem.id} cartItem={cartItem} />
            ))}
            <BottomCart />
        </section>
    );
};

export default CartPage;
