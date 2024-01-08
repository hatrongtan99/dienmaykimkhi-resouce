import React from "react";

const catalogs = [
    {
        id: 1,
        name: "Điện thế pin",
        value: "40V max-4.0Ah",
    },
    {
        id: 2,
        name: "Khả năng khoan thép",
        value: "20mm",
    },
    {
        id: 3,
        name: "Khả năng khoan gỗ",
        value: "76mm",
    },
    {
        id: 4,
        name: "Khả năng khoan tường gạch",
        value: "20mm",
    },
    {
        id: 5,
        name: "Đầu cặp",
        value: "1.5 - 13mm",
    },
    {
        id: 6,
        name: "Tốc độ không tải thấp / cao",
        value: "0 - 650 / 0 - 2.600v/p",
    },
    {
        id: 7,
        name: "Lực đập không tải thấp / cao",
        value: "0 - 9.750 / 0 - 39.000l/p",
    },
    {
        id: 8,
        name: "Lực siết tối đa mềm",
        value: "68Nm",
    },
    {
        id: 9,
        name: "Lực siết tối đa cứng",
        value: "140Nm",
    },
    {
        id: 10,
        name: "Lực siết khóa tối đa",
        value: "125Nm",
    },
    {
        id: 11,
        name: "Kích thước với pin BL4025",
        value: "182x86x275mm",
    },
    {
        id: 12,
        name: "Kích thước với pin BL4040",
        value: "182x86x282mm",
    },
    {
        id: 13,
        name: "Trọng lượng",
        value: "2.7 - 3.0 Kg",
    },
    {
        id: 14,
        name: "Sản xuất tại",
        value: "Trung Quốc",
    },
    {
        id: 15,
        name: "Điện thế pin",
        value: "18V x 1.5Ah",
    },
];

const CatalogSectionDetail = () => {
    return (
        <section className="bg-white h-auto w-full sticky top-[var(--header-height)]">
            <h3 className="text-start text-sm font-bold px-4 py-3">
                THÔNG SỐ KỸ THUẬT
            </h3>
            <ul className="">
                {catalogs.map((item) => (
                    <li className="flex items-center justify-between p-2 text-sm font-normal odd:bg-[#f4f4f4]" key={item.id}>
                        <p className="font-semibold">{item.name}:</p>
                        <span>{item.value}</span>
                    </li>

                ))}
            </ul>
        </section>
    );
};

export default CatalogSectionDetail;
