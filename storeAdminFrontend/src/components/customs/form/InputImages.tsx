import { IoCloudUploadOutline } from "react-icons/io5";

const InputImages = ({ ...rest }) => {
    return (
        <div className="w-full">
            <label
                htmlFor="images"
                className="block border-dashed border-2 rounded-sm cursor-pointer"
            >
                <div className="flex items-center justify-center flex-col min-h-[150px] min-w-[200px]">
                    <IoCloudUploadOutline size={30} color="green" />
                    <span className="text-sm font-light italic">
                        Drag your images here
                    </span>
                </div>
            </label>
            <input type="file" id="images" multiple {...rest} hidden />
        </div>
    );
};

export default InputImages;
