
const AuthhHeader = () => {
    return (
        <div className="text-end mr-2">
            <div className="flex">
                {/* <div className="inline-flex relative ml-auto">
                    <div className=""></div>
                </div> */}

                <div className="flex items-center content-center text-white text-sm p-[2px] cursor-pointer">
                    <p className="hover:text-[rgb(210,210,210)]">
                        Đăng ký
                    </p>
                    <span className="h-[calc(var(--header-height-34px))] w-[1px] bg-white mx-2"></span>
                    <p className="hover:text-[rgb(210,210,210)]">
                        Đăng nhập
                    </p>
                </div>
            </div>
        </div>
    )
}

export default AuthhHeader