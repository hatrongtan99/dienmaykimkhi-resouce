import Button from "@/components/custom/button/Button";
import React from "react";
import { FaCartPlus } from "react-icons/fa";

const BtnCartGroup = () => {
    return (
        <div className="flex space-x-4 mt-4 mb-2">
            <Button
                size="lg"
                variant="primary-border"
                leftIcon={<FaCartPlus />}
            >
                Thêm Vào Giỏ
            </Button>

            <Button size="lg">Mua Ngay</Button>

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
