// import nolist from

const NoList = () => {
    return (
        <div className="bg-white min-h-[400px] flex justify-center items-center flex-col">
            <div className="w-[60px] h-[60px] bg-nolist bg-no-repeat bg-contain bg-center"></div>
            <h3 className="font-normal text-lg mt-2">Chưa có đơn hàng</h3>
        </div>
    );
};

export default NoList;
