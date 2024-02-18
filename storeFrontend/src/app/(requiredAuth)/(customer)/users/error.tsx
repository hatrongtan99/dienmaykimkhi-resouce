"use client"; // Error components must be Client Components

import Button from "@/components/custom/button/Button";
import Image from "next/image";
import { useEffect } from "react";

export default function Error({
    error,
    reset,
}: {
    error: Error & { digest?: string };
    reset: () => void;
}) {
    useEffect(() => {
        console.log(error);
    }, [error]);

    return (
        <div className="w-full min-h-[400px] flex justify-center items-end bg-internal-error bg-contain bg-center bg-no-repeat bg-[#eefbfe]">
            <div className="mb-4">
                <Button
                    variant="primary-border"
                    onClick={() => {
                        reset();
                    }}
                >
                    Try again
                </Button>
            </div>
        </div>
    );
}
