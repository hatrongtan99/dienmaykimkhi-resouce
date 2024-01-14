import { getAllParentCategories } from "@/api/category/category.api";
import { ReactNode } from "react";

export async function generateStaticParams() {
    const listRootCategory = await getAllParentCategories({
        params: "page=0&limit=1000",
    });
    return listRootCategory.records.map((rootCate) => ({
        rootCategorySlug: rootCate.slug,
    }));
}

const RootCategoryLayout = ({ children }: { children: ReactNode }) => {
    return <>{children}</>;
};

export default RootCategoryLayout;
