package com.hatrongtan99.app.utils;

import org.springframework.data.domain.Sort;

public class CommonUtils {
    public static Sort getSort(String typeSort) {
        Sort defaultSort = Sort.unsorted();
        if (typeSort == null) {
            return defaultSort;
        }

        switch (typeSort) {
            case Constant.SORT_QUERY_PRICE_ASC:
                defaultSort = Sort.by(Sort.Direction.ASC, "pr.price");
                break;
            case Constant.SORT_QUERY_PRICE_DESC:
                defaultSort = Sort.by(Sort.Direction.DESC, "pr.price");
                break;
        }

        return defaultSort;
    }

}
