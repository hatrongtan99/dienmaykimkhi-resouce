import LoadingBubble from "@/components/common/loadingIndicator/LoadingBubble";

export default function Loading() {
    return (
        <div className="flex container min-h-[400px] justify-center items-center bg-white">
            <LoadingBubble />
        </div>
    );
}
