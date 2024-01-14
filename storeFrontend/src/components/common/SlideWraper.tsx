"use client"

import { TypeButtonSlide } from "@/types";
import React, { CSSProperties, ReactNode } from "react";
import { GrNext, GrPrevious } from "react-icons/gr";

interface SlideWraperProps {
    children: ReactNode;
    handleMoveSlide: (value: TypeButtonSlide) => void;
    handleTransionEnd: () => void;
    style: CSSProperties
}

const SlideWraper = ({ handleMoveSlide, children, handleTransionEnd, style }: SlideWraperProps) => {
    return (
        <div className="h-auto relative w-auto overflow-hidden bg-white rounded-sm border">
            <div className="slide-btn left-0 top-1/2 -translate-y-1/2">
                <button className="bg-transparent px-0.5 py-2" onClick={() => handleMoveSlide("prev")}>
                    <GrPrevious size={20} />
                </button>
            </div>
            <div onTransitionEnd={handleTransionEnd} style={style} >
                {children}
            </div>
            <div className="slide-btn right-0 top-1/2 -translate-y-1/2">
                <button className="bg-transparent px-0.5 py-2" onClick={() => handleMoveSlide("next")}>
                    <GrNext size={20} />
                </button>
            </div>
        </div>
    );
};

export default SlideWraper;
