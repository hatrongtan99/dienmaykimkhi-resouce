"use client";
import { TypeButtonSlide } from "@/types";
import { BrandResponse } from "@/types/brand.type";
import { useMemo, useState } from "react";
import { useBreakpoint } from "@/hook";
import { v4 as uuid } from "uuid";
import dynamic from "next/dynamic";
import Image from "next/image";
import Link from "next/link";

const SlideWraper = dynamic(() => import("../common/SlideWraper"), {
    ssr: false,
});

interface SlideBrandProps {
    brands: BrandResponse[];
}

const SlideBrand = ({ brands }: SlideBrandProps) => {
    const isLg = useBreakpoint("lg");

    const [imgIndex, setCurentIndex] = useState(1);
    const [needTransition, setNeedTransition] = useState(false);

    const listBrandWithInfinity = useMemo<BrandResponse[]>(() => {
        if ((isLg && brands.length <= 8) || (!isLg && brands.length <= 4)) {
            setCurentIndex(0);
            return brands;
        }
        return [brands[brands.length - 1], ...brands, brands[0]];
    }, [brands]);

    const handleMoveSlide = (type: TypeButtonSlide) => {
        if (type == "next") {
            setCurentIndex((prev) => prev + 1);
        } else {
            setCurentIndex((prev) => prev - 1);
        }
        setNeedTransition(true);
    };

    const handleTransitionEnd = () => {
        if (imgIndex == listBrandWithInfinity.length - 1) {
            setCurentIndex(1);
            setNeedTransition(false);
        } else if (imgIndex == 0) {
            setCurentIndex(listBrandWithInfinity.length - 2);
            setNeedTransition(false);
        }
    };

    const styleTransition = useMemo(() => {
        const colsInRow = isLg ? 8 : 4;
        return {
            transform: `translateX(calc(${imgIndex} * -100%/${colsInRow}))`,
            transition: `${needTransition ? "all 0.25s ease 0s" : "none"}`,
            margin: "0 8px",
            display: "flex",
        };
    }, [needTransition, imgIndex, isLg]);

    return (
        <section className="container">
            <SlideWraper
                handleMoveSlide={handleMoveSlide}
                handleTransionEnd={handleTransitionEnd}
                style={styleTransition}
            >
                {listBrandWithInfinity.map((brand) => {
                    return (
                        <div
                            key={uuid()}
                            className="w-[calc(100%/4)] lg:w-[calc(100%/8)] flex-shrink-0 hover:cursor-pointer relative h-[60px]"
                        >
                            <Link href={`/brand/${brand.slug}`}>
                                <Image
                                    alt={brand.name}
                                    src={brand.thumbnail.url}
                                    className="px-2"
                                    fill
                                />
                            </Link>
                        </div>
                    );
                })}
            </SlideWraper>
        </section>
    );
};

export default SlideBrand;
