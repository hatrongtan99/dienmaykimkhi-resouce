"use client";
import AddressCheckout from "@/components/checkout/AddressCheckout";
import CheckOutItem from "@/components/checkout/CheckOutItem";
import HeadCheckout from "@/components/checkout/HeadCheckout";
import Button from "@/components/custom/button/Button";

const CheckoutPage = () => {
    const handleCheckout = () => {
        fetch(
            "http://localhost:8080/api/v1/payments/bff-customer/payment-online",
            { method: "POST", cache: "no-cache" }
        )
            .then((res) => {
                if (res.ok) {
                    return res.json();
                }
            })
            .then((data) => {
                window.location = data.url;
            });
    };
    return (
        <section className="container">
            <AddressCheckout />
            <HeadCheckout />
            <CheckOutItem />
            <CheckOutItem />
            <CheckOutItem />
            <CheckOutItem />
            <CheckOutItem />

            <div className="text-center">
                <Button variant="primary" as="button" onClick={handleCheckout}>
                    Đặt hàng
                </Button>
            </div>
        </section>
    );
};

export default CheckoutPage;
