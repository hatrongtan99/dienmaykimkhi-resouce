"use client"

import { getAllBrand } from "@/api/brand.api"
import { useEffect } from "react"


const page = () => {

    useEffect(() => {
        (async function () {
            // const data = await getAllBrand();
            // console.log(data)
            const data = await (await fetch("http://localhost:8080/api/v1/products/bff-customer/brands/test", {
                method: "post",
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify({ "body": "data" })
            })).text()
            console.log(data)
        })()
    }, [])

    return (
        <div>page</div>
    )
}

export default page