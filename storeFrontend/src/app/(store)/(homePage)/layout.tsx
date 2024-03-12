import React, { ReactNode } from "react";
import HomeSlideImg from "./HomeSlideImg";

const layout = ({ children }: { children: ReactNode }) => {
    return (
        <div>
            <section className="container">
                <div className="ml-56 mt-2 h-[340px] grid grid-cols-3 ">
                    <div className="ml-2 col-span-2">
                        <HomeSlideImg />
                    </div>
                    <div className="col-span-1">post new</div>
                </div>
            </section>
            {children}
        </div>
    );
};

export default layout;
