import Link from "next/link";
import { MdOutlineKeyboardArrowRight } from "react-icons/md";
import { ClassNameValue, twMerge } from "tailwind-merge";

const categoriesList = [
    { lable: "Dụng cụ điện", path: "" },
    { lable: "Thiết bị đo & Kiểm tra điện", path: "" },
    { lable: "Thiết bị đo cơ khí", path: "" },
    { lable: "Thiết bị đo cơ khí", path: "" },
    { lable: "Thiết bị đo chính xác", path: "" },
    { lable: "Máy kiểm tra nước", path: "" },
    { lable: "Bể rửa siêu âm", path: "" },
    { lable: "Thiết bị quan sát", path: "" },
    { lable: "Danh mục khác", path: "" },
];
interface MenuCategoriesProps {
    className?: ClassNameValue;
}

const MenuCategories = ({ className }: MenuCategoriesProps) => {
    return (
        <div
            className={twMerge(
                "w-56 border shadow-md py-1 shrink-0 bg-white z-40",
                className
            )}
        >
            <ul>
                {categoriesList.map((item, index) => (
                    <li
                        key={index}
                        className="border-b  last:border-b-0 py-2 hover:border-l-4 hover:border-l-secondary-color hover:text-secondary-color "
                    >
                        <Link
                            href={item.path}
                            className="mx-2 flex justify-between items-center"
                        >
                            <span className="text-sm font-normal">
                                {item.lable}
                            </span>
                            <MdOutlineKeyboardArrowRight
                                className="text-text-light-color"
                                size={20}
                            />
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MenuCategories;
