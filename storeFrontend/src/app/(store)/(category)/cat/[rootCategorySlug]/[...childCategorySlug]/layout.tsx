import { ReactNode } from "react";

// export async function generateStaticParams({
//     params: { rootCategorySlug },
// }: {
//     params: { rootCategorySlug: string };
// }) {
//     // const listCategory
//     return [];
// }

const ChildCategoryLayout = ({ children }: { children: ReactNode }) => {
    return <>{children}</>;
};

export default ChildCategoryLayout;
