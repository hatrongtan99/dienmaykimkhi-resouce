"use client";

import SlideWraper from "@/components/common/SlideWraper";
import { TypeButtonSlide } from "@/types";
import Image from "next/image";
import Link from "next/link";
import { useEffect, useLayoutEffect, useRef, useState } from "react";

const HomeSlideImg = () => {
    let urlImg = [
        "/images/slideHome/dungcucamtay-maydochuyendung-1697538245.jpg",
        "/images/slideHome/laser-maydochuyendungcom-1697538465.jpg",
        "/images/slideHome/may-do-nuoc-final-1-1584672707.jpg",
        "/images/slideHome/nhacua-maydochuyendung-1697538426 (1).jpg",
        "/images/slideHome/phu-kien-maydochuyendung-1697538497.jpg",
    ];

    let linkTags = [
        "Voucher DC Cầm Tay",
        "Voucher DC Đo laser & KTS",
        "Voucher DC Nhà Cửa & Sân Vườn",
        "Voucher Phụ Kiện Bosch",
        "Voucher Phụ Kiện Bosch",
    ];
    const refContainerLinkTags = useRef<HTMLDivElement>(null);
    const [currentIndex, setCurrentIndex] = useState(1);
    const [needTransition, setNeedTransition] = useState(false);
    const [isMoving, setIsMoving] = useState(false);

    useLayoutEffect(() => {
        urlImg = [urlImg[urlImg.length - 1], ...urlImg, urlImg[0]];
        linkTags = [linkTags[linkTags.length - 1], ...linkTags, linkTags[0]];
    }, []);

    const handleMoveSlide = (value: TypeButtonSlide) => {
        if (isMoving) return;
        if (value == "next") {
            setCurrentIndex((prev) => prev + 1);
        } else {
            setCurrentIndex((prev) => prev - 1);
        }
        setNeedTransition(true);
        setIsMoving(true);
    };

    const handleTransionEnd = () => {
        if (currentIndex == urlImg.length - 1) {
            setCurrentIndex(1);
            setNeedTransition(false);
        } else if (currentIndex == 0) {
            setCurrentIndex(urlImg.length - 2);
            setNeedTransition(false);
        }
        setIsMoving(false);
    };

    return (
        <div className="h-full">
            <SlideWraper
                style={{
                    transform: `translateX(${currentIndex * -100}%)`,
                    transition: needTransition ? "all 0.2s ease 0s" : "none",
                    display: "flex",
                }}
                handleMoveSlide={handleMoveSlide}
                handleTransionEnd={handleTransionEnd}
            >
                {urlImg.map((url, index) => (
                    <div
                        className="flex-shrink-0 h-[280px] w-full relative"
                        key={index}
                    >
                        <Link href={""} key={index}>
                            <Image src={url} alt="" className="bg-cover" fill />
                        </Link>
                    </div>
                ))}
            </SlideWraper>
            <div
                className={`overflow-hidden bg-white text-center text-xs text-inherit -mt-[1px] relative`}
                ref={refContainerLinkTags}
            >
                <div
                    className="top-0 left-0 border-t-[3px] border-primary-color inline-block absolute w-[25%]"
                    id="border-top"
                    style={
                        {
                            // transform: `translateX(${(currentIndex - 1) * 100}%)`,
                        }
                    }
                ></div>
                <div
                    className="flex"
                    // style={{ transform: `translateX(${currentIndex * -25}%)` }}
                >
                    {linkTags.map((tag, index) => (
                        <div
                            className="flex-shrink-0 w-[calc(100%/4)]"
                            key={index}
                        >
                            <div className="flex items-center justify-center border-l border-r border-stone-300 mt-2 px-2 h-[36px]">
                                {tag}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default HomeSlideImg;
