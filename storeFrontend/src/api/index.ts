const customFetch = <T>(
    url: URL | string,
    options?: RequestInit & { sendForm?: boolean }
): Promise<T> => {
    return new Promise((resolve, reject) => {
        const defaultHeader: { "Content-Type"?: string } = {
            "Content-Type": "application/json",
        };
        if (options && options.sendForm) {
            delete defaultHeader["Content-Type"];
        }
        fetch(url, {
            method: "get",
            ...options,
            headers: {
                ...defaultHeader,
                ...options?.headers,
            },
        })
            .then((res) => {
                if (res.ok) {
                    return resolve(res.json());
                } else {
                    return reject({
                        status: res.status,
                        message: res.statusText,
                    });
                }
            })
            .catch((err) => console.log(err));
    });
};

export default customFetch;
