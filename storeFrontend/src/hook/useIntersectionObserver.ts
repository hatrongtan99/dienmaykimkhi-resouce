import React, { useCallback, useRef, useState } from "react";

const useIntersectionObserver = ({
    isLoading,
    hasMore,
    callback,
}: {
    isLoading: boolean;
    hasMore: boolean;
    callback: () => void;
}) => {
    const observe = useRef<IntersectionObserver>();

    const refElementObserver = useCallback(
        <T extends HTMLElement | null>(node: T) => {
            if (isLoading) return;
            observe.current?.disconnect();
            observe.current = new IntersectionObserver(
                (entries) => {
                    if (entries[0].isIntersecting && hasMore) {
                        callback();
                    }
                },
                { threshold: 1 }
            );
            if (node) {
                observe.current.observe(node);
            }
        },
        [isLoading, hasMore, callback]
    );

    return { refElementObserver };
};

export default useIntersectionObserver;
