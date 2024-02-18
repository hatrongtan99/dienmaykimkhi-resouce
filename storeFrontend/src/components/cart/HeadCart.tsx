import React from "react";

const HeadCart = () => {
    return (
        <div className="flex flex-nowrap py-4 px-4 pr-2 my-3 text-inherit bg-white text-sm font-medium">
            <div className="w-[40%] ml-8">Sản Phẩm</div>
            <div className="flex flex-grow">
                <div className="flex-1 text-center text-stone-500">Đơn Giá</div>
                <div className="flex-1 text-center text-stone-500">
                    Số Lượng
                </div>
                <div className="flex-1 text-center text-stone-500">Số Tiền</div>
                <div className="flex-1 text-center text-stone-500">
                    {" "}
                    Thao Tác
                </div>
            </div>
        </div>
    );
};

export default HeadCart;
