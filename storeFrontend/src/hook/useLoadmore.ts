import { MetadataPage } from "@/types";
import { UndefinedInitialDataOptions, useQuery } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";
import { Dispatch, SetStateAction, useEffect, useRef, useState } from "react";

const useLoadmore = <ResultType, MetadataType = MetadataPage>(
    initialData: { records: ResultType[]; _metadata: MetadataType },
    queryOption: UndefinedInitialDataOptions<typeof initialData>,
    page: number,
    setPage: Dispatch<SetStateAction<number>>
) => {
    const searchParams = useSearchParams();

    const [listResult, setListResult] = useState<ResultType[]>(
        initialData.records
    );
    const [metadata, setMetadata] = useState<MetadataType>(
        initialData._metadata
    );
    const isResetPageRef = useRef<boolean>(false);

    // reset page -> 0 when new params add
    useEffect(() => {
        setPage(0);
        console.log("reset page");
    }, [searchParams]);

    useEffect(() => {
        if (page > 0) {
            isResetPageRef.current = true;
        }
    }, [page]);

    const { data } = useQuery(queryOption);

    useEffect(() => {
        if (data) {
            if (page == 0 && searchParams.toString() == "") {
                setListResult(initialData.records);
                setMetadata(initialData._metadata);
            } else if (page == 0 && searchParams.toString()) {
                setListResult(data.records);
                setMetadata(data._metadata);
            } else if (isResetPageRef.current) {
                setListResult((prev) => {
                    return [...prev, ...data.records];
                });
                setMetadata(data._metadata);
                isResetPageRef.current = false;
            }
        }
    }, [data, page]);

    const handleClickMore = () => {
        setPage((prev) => prev + 1);
    };
    return { handleClickMore, listResult, metadata };
};

export default useLoadmore;
