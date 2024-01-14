"use client"
import { MouseEvent, useMemo, useRef, useState } from "react"
import Image from "next/image"
import SlideWraper from "../../common/SlideWraper"
import { TypeButtonSlide } from "@/types"
import { twMerge } from "tailwind-merge"

const IMAGE_DEFAULT_URL = "/images/defaultImage.webp"
interface SlideDetailProductProps {
    images: string[]
}
const SlideDetailProduct = ({ images }: SlideDetailProductProps) => {

    const [imageIndex, setImageIndex] = useState(0);
    const [isMoving, setIsMoving] = useState(false);
    const [needTransition, setNeedTransion] = useState(false)

    images = useMemo(() => {
        if (images.length == 0) {
            return [IMAGE_DEFAULT_URL]
        } else {
            setImageIndex(1)
            return [images[images.length - 1], ...images, images[0]]
        }
    }, [images])


    const manifyLensRef = useRef<HTMLDivElement[]>([])
    const manifyImgRef = useRef<HTMLDivElement[]>([])

    // zoom images
    const handleMouseMove = (e: MouseEvent<HTMLDivElement>, i: number) => {
        if (images.length == 1) return
        const imgRect = manifyImgRef.current[i].getBoundingClientRect();

        let x = e.pageX - imgRect.left - window.scrollX - manifyLensRef.current[i].offsetWidth / 2
        let y = e.pageY - imgRect.top - window.scrollY - manifyLensRef.current[i].offsetHeight / 2

        const MAX_X = imgRect.width - manifyLensRef.current[i].offsetWidth;
        const MAX_Y = imgRect.height - manifyLensRef.current[i].offsetHeight;

        if (x > MAX_X) x = MAX_X;
        if (x < 0) x = 0
        if (y > MAX_Y) y = MAX_Y;
        if (y < 0) y = 0

        manifyLensRef.current[i].style.cssText = `
            opacity: 1;
            background: url(${images[i]}) -${x * 2}px -${y * 2}px / ${imgRect.width * 2}px ${imgRect.height * 2}px no-repeat;
            top: ${y}px; left: ${x}px;
        `;
    }

    const handleMouseOut = (E: MouseEvent<HTMLDivElement>, i: number) => {
        manifyLensRef.current[i].style.background = "unset"
        manifyLensRef.current[i].style.opacity = "0"
    }

    // btn click slide next | prev
    const handleMoveSlide = (type: TypeButtonSlide) => {
        if (isMoving || images.length == 1) return
        if (type == "next") {
            setImageIndex(prev => prev + 1)
        } else {
            setImageIndex(prev => prev - 1)
        }
        setIsMoving(true)
        setNeedTransion(true)
    }

    const handleTransionEnd = () => {
        if (imageIndex == images.length - 1) {
            setImageIndex(1)
            setNeedTransion(false)
        } else if (imageIndex == 0) {
            setImageIndex(images.length - 2)
            setNeedTransion(false)
        }
        setIsMoving(false)
    }

    const styleTransition = useMemo(() => {
        return {
            transform: `translateX(${imageIndex * -100}%)`,
            transition: `${needTransition ? "all 0.25s ease 0s" : "none"}`,
            display: "flex"
        };
    }, [needTransition, imageIndex])

    return (
        <div>
            <SlideWraper
                handleMoveSlide={handleMoveSlide}
                handleTransionEnd={handleTransionEnd}
                style={styleTransition}
            >
                {images.map((img, index) => {
                    return (
                        <div
                            className="min-h-[400px] w-full flex-shrink-0 relative border overflow-hidden "
                            key={index}
                        >
                            <Image
                                alt=""
                                src={img}
                                className="bg-cover"
                                fill
                                onMouseMove={(e) =>
                                    handleMouseMove(e, index)
                                }
                                onMouseLeave={(e) => handleMouseOut(e, index)}
                                ref={(el) => el && (manifyImgRef.current[index] = el)}
                            />
                            {/* lens */}
                            <div
                                className="absolute w-[150px] h-[150px] rounded-full pointer-events-none border opacity-0"
                                ref={(el) =>
                                    el &&
                                    (manifyLensRef.current[index] = el)
                                }
                            ></div>

                        </div>
                    );
                })}
            </SlideWraper >

            < div className="flex space-x-1 mt-2" >
                {
                    images.map((img, index) => {
                        if (index == 0 || index == images.length - 1) return;
                        return (
                            <div
                                className={twMerge(
                                    "w-[70px] border h-auto cursor-pointer ",
                                    imageIndex == index &&
                                    "border border-primary-color"
                                )}
                                key={index}
                            >
                                <Image
                                    src={img}
                                    onClick={() => setImageIndex(index)}
                                    width={70}
                                    height={70}
                                    alt={img}
                                />
                            </div>
                        );
                    })
                }
            </ div>
        </div >
    )
}

export default SlideDetailProduct