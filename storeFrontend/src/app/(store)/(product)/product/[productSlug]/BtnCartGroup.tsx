"use client";
import Button from "@/components/custom/button/Button";
import { FaCartPlus } from "react-icons/fa";
import { useContext } from "react";
import { CartContext } from "@/context/CartContextProvider";
import { useRouter } from "next/navigation";

const BtnCartGroup = ({
    productId,
    disableBtn,
}: {
    productId: number;
    disableBtn: boolean;
}) => {
    const router = useRouter();
    const { handleAddOrUpdateCartItem } = useContext(CartContext);

    const handleAddIntoCart = async () => {
        try {
            await handleAddOrUpdateCartItem({ productId, quantity: 1 }, "add");
        } catch (error) {
            console.log(error);
        }
    };

    const handleBuyImediatly = async () => {
        try {
            await handleAddOrUpdateCartItem({ productId, quantity: 1 }, "add");
            router.push("/cart");
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div className="flex space-x-4 mt-4 mb-2">
            <Button
                size="lg"
                variant="primary-border"
                leftIcon={<FaCartPlus />}
                as="button"
                onClick={handleAddIntoCart}
                disable={disableBtn}
            >
                Thêm Vào Giỏ
            </Button>

            <Button
                size="lg"
                as="button"
                onClick={handleBuyImediatly}
                disable={disableBtn}
            >
                Mua Ngay
            </Button>

            <Button
                size="lg"
                variant="secondary"
                // onClick={() => alert("Tương tác")}
            >
                Cần Tư Vấn
            </Button>
        </div>
    );
};

export default BtnCartGroup;
