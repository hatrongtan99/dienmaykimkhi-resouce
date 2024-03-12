import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";
import React from "react";

const loading = () => {
    return (
        <div className="flex justify-center items-center min-h-[400px] bg-white ">
            <LoadingBubble />
        </div>
    );
};

export default loading;
