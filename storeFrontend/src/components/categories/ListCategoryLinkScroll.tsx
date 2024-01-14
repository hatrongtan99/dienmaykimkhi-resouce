"use client";
import { CategoryResponse } from "@/types/categories.type";
import Link from "next/link";
import { useParams, usePathname } from "next/navigation";

interface ListCategoryLinkScrollProps {
    categories: CategoryResponse[];
}

const ListCategoryLinkScroll = ({
    categories,
}: ListCategoryLinkScrollProps) => {
    const pathname = usePathname();
    const params = useParams();
    const pathSlug = (slug: string) => {
        let result = `/cat/${params.rootCategorySlug}`;
        const listChildSlug = params.childCategorySlug as string[];

        if (listChildSlug.length > 1) {
            return (
                result +
                "/" +
                listChildSlug.slice(0, listChildSlug.length - 1).join("/") +
                "/" +
                slug
            );
        } else {
            return result + "/" + slug;
        }
    };
    return (
        <section className="pl-1 py-1 bg-white">
            <div className="w-full max-h-[300px] overflow-y-scroll">
                <ul>
                    {categories.map((cate, index) => {
                        const path = pathSlug(cate.slug);
                        const isActive = pathname === path;
                        return (
                            <li
                                key={index}
                                className={`border-b last:border-none text-secondary-color hover:bg-primary-light-color hover:text-white rounded-sm ${
                                    isActive && "bg-primary-color text-white"
                                }`}
                            >
                                <Link
                                    href={`${path}`}
                                    className="py-1.5 px-2 text-sm font-normal text-inherit block capitalize"
                                >
                                    {cate.name}
                                </Link>
                            </li>
                        );
                    })}
                </ul>
            </div>
        </section>
    );
};

export default ListCategoryLinkScroll;
