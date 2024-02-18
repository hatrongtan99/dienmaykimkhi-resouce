"use client";
import Link from "next/link";
import Image from "next/image";
import { CategoryResponse } from "@/types/products/categories.type";
import { usePathname } from "next/navigation";

interface ListCategoryLinkCardProps {
    categories: CategoryResponse[];
}

const ListCategoryLinkCard = ({ categories }: ListCategoryLinkCardProps) => {
    return (
        <section className="mb-4">
            <div className="flex flex-wrap">
                {categories.map((category) => (
                    <CategoryLinkCardItem
                        key={category.id}
                        categoryItem={category}
                    />
                ))}
            </div>
        </section>
    );
};

const CategoryLinkCardItem = ({
    categoryItem,
}: {
    categoryItem: CategoryResponse;
}) => {
    const pathname = usePathname();
    const { slug, name, thumbnail } = categoryItem;
    return (
        <div className="border bg-white group overflow-hidden h-auto w-1/4 md:w-1/6 lg:w-[calc(100%/8)]">
            <div className="px-2 py-1 text-center">
                <Link href={`${pathname}/${slug}`}>
                    <Image
                        width={60}
                        height={60}
                        src={thumbnail ? thumbnail.url : ""}
                        alt={name}
                        className="cover bg-center group-hover:scale-105 mx-auto"
                    />
                    <h4 className="text-sm group-hover:text-secondary-color">
                        {name}
                    </h4>
                </Link>
            </div>
        </div>
    );
};

export default ListCategoryLinkCard;
