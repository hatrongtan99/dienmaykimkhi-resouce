import Button from "@/components/custom/button/Button";
import React from "react";
import { AiTwotoneStar } from "react-icons/ai";

const RatingBox = () => {
    return (
        <section className="my-4">
            <div className="flex items-center justify-between">
                <h3 className="text-sm font-semibold uppercase">
                    Đánh giá sản phẩm
                </h3>
                <Button size="sm">Đánh Giá Ngay</Button>
            </div>
            <div className="mt-2 px-2 py-4 border rounded-md">
                <div className="flex items-center">
                    <span className="text-lg font-bold text-primary-color">
                        0
                    </span>
                    <div className="flex space-x-1 mx-2">
                        {new Array(5).fill(0).map((_, index) => (
                            <AiTwotoneStar
                                size={18}
                                color={index + 1 <= 1 ? "#f4c91f" : "#ddd"}
                                key={index}
                            />
                        ))}
                    </div>
                    <span className="text-sm font-medium">{10} đánh giá</span>
                </div>

                <div className=""></div>
            </div>
        </section>
    );
};

export default RatingBox;
