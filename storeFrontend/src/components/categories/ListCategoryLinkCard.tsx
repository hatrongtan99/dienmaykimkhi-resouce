import React from 'react'
import Link from 'next/link'
import Image from 'next/image'

interface ListCategoryLinkCardProps {

}

const ListCategoryLinkCard = () => {
    return (
        <section className="">
            <div className='flex flex-wrap'>
                {(new Array(20)).fill(0).map((_, index) => (
                    <CategoryLinkCardItem key={index} img={"/images/categories/x50-drill-1583900054.png"} path='/' title={"Máy khoan" + index} />
                ))}
            </div>
        </section>
    )
}




interface CategoryLinkCardItemProps {
    img: string,
    path: string,
    title: string
}

const CategoryLinkCardItem = ({ img, path, title }: CategoryLinkCardItemProps) => {
    return (
        <div className='border  bg-white group overflow-hidden h-auto w-1/4 md:w-1/6 lg:w-[120px] lg:h-[110px]'>
            <div className='px-2 py-1 text-center'>
                <Link href={path}>
                    <Image width={60} height={60} src={img} alt={title} className='cover bg-center group-hover:scale-105 mx-auto' />
                    <h4 className='text-sm group-hover:text-secondary-color'>{title}</h4>
                </Link>
            </div>
        </div>
    )
}

export default ListCategoryLinkCard