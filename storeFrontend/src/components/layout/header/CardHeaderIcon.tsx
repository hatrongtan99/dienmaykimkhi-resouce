import React from 'react'
import { BsFillCartCheckFill } from 'react-icons/bs'

const CardHeaderIcon = () => {
    return (
        <div className="hover:cursor-pointer p-2 relative rounded-1/2 group">
            {/* cart icon */}
            <BsFillCartCheckFill
                size={24}
                color="#fff"
                className='group-hover:scale-110 transition-transform ease-linear duration-100'
            />
            <div className="flex items-center justify-center absolute h-5 w-5 top-0 left-5 bg-white rounded-1/2 text-primary-color">
                <span className="font-normal">10</span>
            </div>
        </div>
    )
}

export default CardHeaderIcon