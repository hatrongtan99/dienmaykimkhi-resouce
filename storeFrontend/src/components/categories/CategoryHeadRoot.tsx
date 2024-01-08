import Link from 'next/link'
import React from 'react'
import SlideWraper from '../common/SlideWraper'
import CardMainProduct from '../product/CardMainProduct'

const CategoryHeadRoot = ({ title }: { title: string }) => {
    return (
        <div>
            <div className='bg-white py-2 px-3 mt-4'>
                <Link href={"/"} className='rounded-sm uppercase text-xl font-semibold pl-3 border-l-[3px] border-l-red-600 text-secondary-color hover:text-secondary-light-color'>
                    {title}
                </Link>
            </div>

            <div className="mt-0.5">
                <SlideWraper
                    handleMoveSlide={() => { }}
                    handleTransionEnd={() => {

                    }}
                    style={{}}
                >
                    <div className="grid grid-cols-4 lg:grid-cols-5 gap-[2px] mt-[2px]">
                        <CardMainProduct productItem={null} hasBrandImg />
                        <CardMainProduct productItem={null} hasBrandImg />
                        <CardMainProduct productItem={null} hasBrandImg />
                        <CardMainProduct productItem={null} hasBrandImg />
                    </div>
                </SlideWraper>
            </div>

        </div>
    )
}

export default CategoryHeadRoot