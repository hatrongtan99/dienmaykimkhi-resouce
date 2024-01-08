"use client"

import Button from '@/components/custom/button/Button';
import descriptionContent from '@/data/description';
import React, { useEffect, useRef, useState } from 'react'
import { IoMdArrowDropdown, IoMdArrowDropup } from "react-icons/io";
import { twMerge } from 'tailwind-merge'

const ProductDescription = () => {
    const [isShowFull, setIsShowFull] = useState(false);
    const [showBtn, setShowBtn] = useState(true);
    const descriptionRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        if (descriptionRef.current != null) {
            const x = descriptionRef.current.getBoundingClientRect();
            if (x && x.height <= 500) {
                setShowBtn(false)
            }
        }
    }, [descriptionRef.current])

    return (
        <section className={twMerge("relative text-text-color text-justify my-4 max-h-[500px] overflow-hidden transition duration-1000 ease-linear", isShowFull && "max-h-none h-auto")}>
            <div
                dangerouslySetInnerHTML={{ __html: descriptionContent }}
                ref={descriptionRef}
                id="description-content"
                className='text-justify'
            />
            {showBtn && (
                <>
                    <div
                        className={twMerge("absolute w-full h-2/5 bottom-0 right-0 bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,0.8)]", isShowFull && "hidden")}
                    >
                        <Button
                            size="sm"
                            variant="secondary-border"
                            rightIcon={<IoMdArrowDropdown />}
                            onClick={() => setIsShowFull(true)}
                            className="absolute bottom-3 left-1/2 -translate-x-1/2"
                        >
                            Xem thêm nội dung
                        </Button>
                    </div>
                    <div className="flex justify-center">
                        <Button
                            size="sm"
                            variant="secondary-border"
                            rightIcon={<IoMdArrowDropup />}
                            onClick={() => setIsShowFull(false)}
                        >
                            Thu Gọn
                        </Button>
                    </div>
                </>
            )}
        </section>
    )
}

export default ProductDescription