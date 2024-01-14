import React from "react";
import { BsCheckLg } from "react-icons/bs";

const PolicySection = () => {
    return (
        <section>
            <div className="my-[10px] p-3 w-full h-auto min-h-[100px] border  border-solid">
                <h3 className="text-[#1da04e] mb-[10px] text-sm font-semibold">
                    LỢI ÍCH KHI MUA
                </h3>
                <div className="flex items-center mb-2">
                    <BsCheckLg color="#007f00" />
                    <p className="text-sm font-normal text-text-color ml-[10px]">
                        Sản phẩm chính hãng 100%
                    </p>
                </div>
                <div className="flex items-center mb-2">
                    <BsCheckLg color="#007f00" />
                    <p className="text-sm font-normal text-text-color ml-[10px]">
                        Giá luôn tốt nhất
                    </p>
                </div>
                <div className="flex items-center mb-2">
                    <BsCheckLg color="#007f00" />
                    <p className="text-sm font-normal text-text-color ml-[10px]">
                        Tư vấn chuyên nghiệp
                    </p>
                </div>
                <div className="flex items-center mb-2">
                    <BsCheckLg color="#007f00" />
                    <p className="text-sm font-normal text-text-color ml-[10px]">
                        Giao hàng toàn quốc
                    </p>
                </div>
                <div className="flex items-center mb-0">
                    <BsCheckLg color="#007f00" />
                    <p className="text-sm font-normal text-text-color ml-[10px]">
                        Bảo hành &amp; sửa chữa tận tâm
                    </p>
                </div>
            </div>
        </section>
    );
};

export default PolicySection;
