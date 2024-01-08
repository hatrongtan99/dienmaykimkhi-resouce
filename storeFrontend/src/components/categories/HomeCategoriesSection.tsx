import Link from 'next/link'
import React from 'react'
import CardMainProduct from '../product/CardMainProduct'

const HomeCategoriesSection = () => {
    return (
        <section className='container mt-4'>
            <div className='flex border-b-2 border-primary-color bg-white'>
                <Link href={""} className='bg-primary-color rounded-sm'><h3 className='text-white font-semibold uppercase px-4 py-2'>máy khoan</h3></Link >
                <Link href={""} ><h3 className='px-4 py-2 capitalize text-sm text-secondary-color'>máy khoan pin</h3></Link >
                <Link href={""} ><h3 className='px-4 py-2 capitalize text-sm text-secondary-color'>máy khoan búa bê tông</h3></Link>
                <Link href={""} ><h3 className='px-4 py-2 capitalize text-sm text-secondary-color'>máy khoan Bosch </h3></Link>
                <Link href={""} ><h3 className='px-4 py-2 capitalize text-sm text-secondary-color'>máy khoan makita</h3></Link>
                <Link href={""} className='flex flex-grow' ><h3 className='px-4 py-2 capitalize text-sm text-secondary-color ml-auto'>danh mục khác</h3></Link>
            </div>
            {/* list product */}
            <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 container gap-[2px]">
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />
                <CardMainProduct productItem={""} />

            </div>
        </section>
    )
}

export default HomeCategoriesSection