import { BiSolidDiscount } from "react-icons/bi";
import Button from "../custom/button/Button";

const VoucherCheckout = () => {
    return (
        <div className="flex px-6 py-6 mt-3 mb-1 bg-white font-normal justify-between items-center">
            <div className="grow flex">
                <BiSolidDiscount size={28} fill="#ee4d2d" />
                <span className="ml-2">Voucher</span>
            </div>
            <div className="">
                <Button variant="text" className="text-sm">
                    Chọn Voucher
                </Button>
            </div>
        </div>
    );
};

export default VoucherCheckout;
