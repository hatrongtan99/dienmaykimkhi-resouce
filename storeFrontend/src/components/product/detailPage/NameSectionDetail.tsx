import Link from "next/link";
import { AiFillStar } from "react-icons/ai";

interface NameSectionDetailProps {
    name: string;
}

const NameSectionDetail = ({ name }: NameSectionDetailProps) => {
    const separate = "inline-block h-5 border-r border-r-stone-400 mx-2";
    return (
        <div className="">
            <h1 className="font-medium capitalize">{name}</h1>
            {/* star evaluate */}
            <div className="flex items-center my-3">
                <span className="underline-offset-4 underline mr-2 text-base font-medium">
                    0
                </span>

                <div className="flex space-x-0.5">
                    {new Array(5).fill(0).map((_, index) => (
                        <AiFillStar
                            key={index}
                            size={16}
                            color={index + 1 <= 3 ? "#f4c91f" : "#ddd"}
                            style={{ stroke: "#f6ab27", strokeWidth: "10" }}
                        />
                    ))}
                </div>

                <span className={separate}></span>

                <p className="text-base font-medium underline underline-offset-4">
                    {3}
                    <Link
                        href={"#"}
                        className="text-sm no-underline inline-block text-secondary-color cursor-pointer"
                    >
                        &nbsp;đánh giá
                    </Link>
                </p>

                <span className={separate}></span>

                <p className="text-base font-medium underline underline-offset-4">
                    {3}
                    <span className="text-sm no-underline text-text-light-color inline-block">
                        &nbsp;đã bán
                    </span>
                </p>
            </div>
        </div>
    );
};

export default NameSectionDetail;
