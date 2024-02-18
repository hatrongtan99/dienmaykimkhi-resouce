import { ProductAtrributeResponse } from "@/types/products/attribute.type";

interface CatalogSectionDetailProps {
    attributes: ProductAtrributeResponse[];
}
const CatalogSectionDetail = ({ attributes }: CatalogSectionDetailProps) => {
    return (
        <section className="bg-white h-auto w-full sticky top-[var(--header-height)]">
            {attributes.map((att) => (
                <>
                    {att.isActive ? (
                        <div className="my-2" key={att.id}>
                            <h3 className="text-start text-sm font-bold px-4 py-3 bg-primary-light-color text-white uppercase">
                                {att.name}
                            </h3>
                            <ul className="">
                                {att.items.map((item) => (
                                    <li
                                        className="flex items-center justify-between p-2 text-sm font-normal odd:bg-[#f4f4f4]"
                                        key={item.id}
                                    >
                                        <p className="font-semibold">
                                            {item.name}:
                                        </p>
                                        <span className="text-sm font-normal">
                                            {item.value}
                                        </span>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ) : null}
                </>
            ))}
        </section>
    );
};

export default CatalogSectionDetail;
