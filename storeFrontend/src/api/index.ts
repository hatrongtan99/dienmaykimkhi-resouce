const BASE_URL_API = process.env.NEXT_PUBLIC_BASE_URL_API;

const customFetch = <T>(
    url: URL | string,
    options?: RequestInit & { sendForm?: boolean }
): Promise<T> => {
    return new Promise((resolve, reject) => {
        if (!BASE_URL_API) {
            throw new Error("base url api not is undefined");
        }
        const defaultHeader: { "Content-Type"?: string } = {
            "Content-Type": "application/json",
        };
        if (options && options.sendForm) {
            delete defaultHeader["Content-Type"];
        }
        fetch(BASE_URL_API + url, {
            method: "get",
            ...options,
            headers: {
                "Content-type": "application/json",
                // ...options?.headers,
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
