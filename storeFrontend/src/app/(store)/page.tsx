"use client"

import { getAllBrand } from "@/api/brand.api";
import SlideBrand from "@/components/brands/SlideBrand";
import HomeCategoriesSection from "@/components/categories/HomeCategoriesSection";
import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

export default function Home() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["all-brand"],
    queryFn: getAllBrand
  })

  console.log(data)

  // useEffect(() => {
  //   fetch("http://localhost:8080/api/v1/products/bff-customer/brands").then(data => data.json()).then(data => console.log(data))
  // }, [])

  return (
    <>
      <section className="container">
        <div className="ml-56 h-[350px]">
          <div className="ml-2 h-full">img slide</div>
        </div>
      </section>
      <SlideBrand brands={[]} />
      <HomeCategoriesSection />
      <HomeCategoriesSection />
      <HomeCategoriesSection />
      <HomeCategoriesSection />
    </>
  )
}
