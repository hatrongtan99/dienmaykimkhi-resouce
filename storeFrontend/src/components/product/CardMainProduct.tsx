import Image from 'next/image'
import Link from 'next/link'
interface CardMainProduct {
    hasBrandImg?: boolean
    productItem: any
}

const CardMainProduct = ({
    hasBrandImg, productItem
}: CardMainProduct) => {
    return (
        <div className="rounded-sm bg-white h-full overflow-hidden group hover:border hover:border-red-300 shadow-lg ">
            <div className='px-4 py-3'>
                <Link href={""} className='inline-block'>
                    <Image alt='' src={"/images/products/320x320-GSB-120-li-2019.jpg"} width={200} height={200} className='group-hover:scale-105 transition-all duration-150' />
                    <h6 className='text-sm font-medium tracking-wide leading-5 mt-2'>Máy khoan động lực chống nước 50m Nemo Mỹ</h6>
                </Link>

                {true ? (
                    <Link href="/" className='border border-gray-200 rounded-sm inline-block'>
                        <Image
                            alt={``}
                            src={"/images/products/x35-bosch-1584089519.png"}
                            height={30}
                            width={60}
                        />
                    </Link>
                ) : null}
                <div className=''>
                    <strong className='text-red-600 font-bold'>{(10000).toLocaleString()} đ</strong>
                    {true ? <div></div> : null}
                </div>
            </div>


        </div>
    )
}

export default CardMainProduct