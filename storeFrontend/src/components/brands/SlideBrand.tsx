"use client"
import dynamic from 'next/dynamic'
import { TypeButtonSlide } from '@/types'
import { allBrands } from "@/data/brand"
import Image from 'next/image'
import Link from 'next/link'
import { BrandResponse } from '@/types/brand.type'
import { useMemo, useState } from 'react'
import { useBreakpoint } from '@/hook'

const SlideWraper = dynamic(() => import("../common/SlideWraper"), { ssr: false })

interface SlideBrandProps {
    brands: BrandResponse[]
}

const SlideBrand = ({ brands }: SlideBrandProps) => {
    const isLg = useBreakpoint("lg")

    brands = allBrands;

    const [imgIndex, setCurentIndex] = useState(1);
    const [needTransition, setNeedTransition] = useState(false);

    const listBrandWithInfinity = useMemo<BrandResponse[]>(() => {
        if (isLg && brands.length <= 8 || !isLg && brands.length <= 4) {
            setCurentIndex(0)
            return brands
        }
        return [brands[brands.length - 1], ...brands, brands[0]]
    }, [brands])


    const handleMoveSlide = (type: TypeButtonSlide) => {
        if (type == "next") {
            setCurentIndex(prev => prev + 1);
        } else {
            setCurentIndex(prev => prev - 1)
        }
        setNeedTransition(true)
    }

    const handleTransitionEnd = () => {
        if (imgIndex == listBrandWithInfinity.length - 1) {
            setCurentIndex(1)
            setNeedTransition(false)
        } else if (imgIndex == 0) {
            setCurentIndex(listBrandWithInfinity.length - 2)
            setNeedTransition(false)
        }

    }

    const styleTransition = useMemo(() => {
        const colsInRow = isLg ? 8 : 4
        return {
            transform: `translateX(calc(${imgIndex} * -100%/${colsInRow}))`,
            transition: `${needTransition ? "all 0.25s ease 0s" : "none"}`,
        }
    }, [needTransition, imgIndex, isLg])


    return (
        <section className='container'>
            <SlideWraper
                handleMoveSlide={handleMoveSlide}
                handleTransionEnd={handleTransitionEnd}
                style={styleTransition}
            >
                <div className='flex my-2 min-h-[60px]'>
                    {listBrandWithInfinity.map((brand, index) => {
                        return <div
                            key={index}
                            className='w-[calc(100%/4)] lg:w-[calc(100%/8)] h-[60px] flex-shrink-0 hover:cursor-pointer'
                        >
                            <Link href={`/brand/${brand.slug}`} className='w-full h-full '>
                                <Image
                                    alt={brand.name}
                                    src={brand.thumbnail.url}
                                    className='px-7'
                                    width={200}
                                    height={150}
                                />
                            </Link>

                        </div>
                    })}
                </div>

            </SlideWraper>
        </section>
    )
}

export default SlideBrand