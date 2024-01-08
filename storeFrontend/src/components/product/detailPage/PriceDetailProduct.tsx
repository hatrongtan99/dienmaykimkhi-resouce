import Link from 'next/link'
import { FaCartPlus } from "react-icons/fa";
import { MdOutlineLocalShipping } from "react-icons/md";
import { IoIosArrowDown } from "react-icons/io";

import Button from '../../custom/button/Button'

interface PriceDetailProductProps {

}

const PriceDetailProduct = () => {
    return (
        <section>
            <div className="border-b border-solid ">

                <div className='flex space-x-4 py-4 px-5 bg-stone-200 rounded-sm mb-4'>
                    <span className='text-sm line-through inline-block text-text-light-color'>
                        {(200000).toLocaleString()} đ
                    </span>
                    <strong className='text-red-600 text-xl font-semibold'>{(100000).toLocaleString()} đ</strong>{" "}
                    <span className='bg-red-500 text-white text-xs h-[16px] px-[2px] mt-1 inline-block'>giảm 10%</span>
                    <span className='text-sm mt-1'> (đã bao gồm VAT)</span>
                </div>
                {/* shipping */}
                <div className='price-box-line'>
                    <span className='price-box-line__lable'>
                        Vận chuyển:
                    </span>
                    <div className='inline-flex space-x-2 items-center'>
                        <MdOutlineLocalShipping size={20} />
                        <span className='!text-text-light-color'>Vận chuyển tới</span>
                        <Button variant='secondary-border' rightIcon={<IoIosArrowDown />} size='sm'>
                            Địa Chỉ
                        </Button>
                    </div>
                </div>

                <div className="price-box-line">
                    <span className='price-box-line__lable'>
                        Hãng:
                    </span>
                    <Link href="/">makita</Link>
                </div>

                <div className="price-box-line">
                    <span className='price-box-line__lable'>Mã sản phẩm: </span>
                    <span>DF488DWE</span>
                </div>
                <div className="price-box-line">
                    <span className='price-box-line__lable'>Bảo hành: </span>
                    <span>12 Tháng</span>
                </div>
                <div className="price-box-line">
                    <span className='price-box-line__lable'>
                        Tình trạng:
                    </span>
                    <span>{2 >= 1 ? "Còn hàng" : "Hết hàng"}</span>
                </div>
            </div>
            <div className="flex space-x-4 mt-4 mb-2">
                <Button
                    size='lg'
                    variant="primary-border"
                    leftIcon={<FaCartPlus />}
                >
                    Thêm Vào Giỏ
                </Button>

                <Button size='lg'>
                    Mua Ngay
                </Button>

                <Button
                    size='lg'
                    variant="secondary"
                // onClick={() => alert("Tương tác")}
                >
                    Cần Tư Vấn
                </Button>
            </div>
        </section>
    )
}

export default PriceDetailProduct