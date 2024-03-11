import React from "react";

const HeadCheckout = () => {
    return (
        <div className="flex py-6 px-6 mt-3 mb-1 bg-white text-sm font-medium">
            <div className="w-[40%] lg:w-[50%] text-lg">Sản Phẩm</div>
            <div className="lg:flex-1 w-6"></div>
            <div className="flex-1 text-center text-stone-500">Đơn Giá</div>
            <div className="flex-1 text-center text-stone-500">Số Lượng</div>
            <div className="flex-[2] text-right text-stone-500">Thành Tiền</div>
        </div>
    );
};

export default HeadCheckout;
