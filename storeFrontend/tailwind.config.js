/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    theme: {
        extend: {
            backgroundImage: {},
            colors: {
                "primary-color": "var(--primary-color)",
                "primary-light-color": "var(--primary-light-color)",
                "secondary-color": "var(--secondary-color)",
                "secondary-light-color": "var(--secondary-light-color)",
                "text-color": "var(--text-color)",
                "text-light-color": "var(--text-light-color)",
            },
            borderRadius: {
                "1/2": "50%",
            },
        },
        container: {
            center: true,
            screens: {
                sm: "100%",
                md: "100%",
                lg: "1024px",
                xl: "1200px",
            },
        },
    },
    plugins: [],
};
