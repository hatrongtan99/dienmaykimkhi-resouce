import Link from "next/link"

const listCategoryScroll = [
    {
        lable: "Máy khoan",
        path: "/"
    },
    {
        lable: "Máy siết bulong",
        path: "/"
    },
    {
        lable: "Máy mài cầm tay",
        path: "/"
    }, {
        lable: "Máy cưa",
        path: "/"
    },
    {
        lable: "Máy cắt",
        path: "/"
    },
    {
        lable: "Máy hàn",
        path: "/"
    },
    {
        lable: "Máy bắt vạn ốc vít",
        path: "/"
    },
    {
        lable: "Máy rửa xe",
        path: "/"
    },
    {
        lable: "Máy chà nhám",
        path: "/"
    },
    {
        lable: "Máy đục bê tông",
        path: "/"
    },
    {
        lable: "Máy cắt gạch",
        path: "/"
    },
    {
        lable: "Máy phay",
        path: "/"
    },
    {
        lable: "Máy đánh bóng",
        path: "/"
    },
    {
        lable: "Máy hàn thiếc",
        path: "/"
    },

    {
        lable: "Máy khuấy trộn sơn",
        path: "/"
    },
    {
        lable: "Máy bắn keo",
        path: "/"
    },
    {
        lable: "Máy thổi bụi",
        path: "/"
    },
    {
        lable: "Máy hút bụi",
        path: "/"
    },
    {
        lable: "Máy bào gỗ",
        path: "/"
    },
    {
        lable: "Máy khò nhiệt",
        path: "/"
    },
]

interface ListCategoryScrollProps {
    categories: { lable: string, path: string }[]
}

const ListCategoryScroll = ({ categories }: ListCategoryScrollProps) => {
    categories = listCategoryScroll
    return (
        <section className='pl-1 py-1 bg-white'>
            <div className='w-full h-[300px] overflow-y-scroll'>
                <ul>
                    {categories.map((item, index) => (
                        <li key={index} className="border-b last:border-none text-secondary-color hover:bg-primary-light-color hover:text-white rounded-sm first:text-white first:bg-primary-color">
                            <Link href={item.path} className="py-1.5 px-2 text-sm font-normal text-inherit block capitalize">
                                {item.lable}
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>

        </section>
    )
}

export default ListCategoryScroll