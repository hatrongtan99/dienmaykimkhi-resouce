"use client";

import CategoryHeadRoot from "@/components/categories/CategoryHeadRoot";
import Button from "@/components/custom/button/Button";
import { CategoriesChildResponse } from "@/types/categories.type";
import { useState } from "react";

const RANGE_CATEGORY_PREVIEW = 4;

interface ListCategoryInPageProps {
    initDataCategories: CategoriesChildResponse;
}

const ListCategoryInPage = ({
    initDataCategories,
}: ListCategoryInPageProps) => {
    const { _metadata, records } = initDataCategories;
    const [isShowMore, setIsShowMore] = useState(false);
    return (
        <div className="mb-4">
            {records.map((cate, index) => {
                return (
                    <div
                        key={cate.id}
                        className={
                            !isShowMore && !(index < RANGE_CATEGORY_PREVIEW)
                                ? "hidden"
                                : "block"
                        }
                    >
                        <CategoryHeadRoot category={cate} key={cate.id} />
                    </div>
                );
            })}
            <div className="text-center">
                {!isShowMore && _metadata.pageSize >= RANGE_CATEGORY_PREVIEW ? (
                    <Button
                        variant="secondary"
                        as={"button"}
                        onClick={() => {
                            setIsShowMore(true);
                        }}
                    >
                        Xem thêm {_metadata.pageSize - RANGE_CATEGORY_PREVIEW}{" "}
                        chỉ mục con
                    </Button>
                ) : null}
            </div>
        </div>
    );
};

export default ListCategoryInPage;
