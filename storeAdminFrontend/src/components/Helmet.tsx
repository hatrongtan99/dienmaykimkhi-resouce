import { ReactNode } from "react";

const Helmet = ({
    title,
    children,
}: {
    title: string;
    children: ReactNode;
}) => {
    document.title = `${title}`;
    return <>{children}</>;
};

export default Helmet;
