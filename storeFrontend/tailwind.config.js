/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    theme: {
        extend: {
            backgroundImage: {
                "internal-error": "url('/images/loi-http-error-4.png')",
            },
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
            keyframes: {
                fadeIn: {
                    "0%": {
                        transform: "scale(0)",
                    },
                    "100%": {
                        transform: "scale(1)",
                    },
                },

                fadeOut: {
                    "0%": {
                        transform: "scale(1)",
                    },
                    "100%": {
                        transform: "scale(0)",
                    },
                },

                "dot-bounce": {
                    "0%, 60%, 100%": {
                        transform: "translateY(0)",
                    },
                    "30%": {
                        transform: "translateY(-4px)",
                    },
                },
            },
            animation: {
                fadeIn: "fadeIn 0.2s ease-in-out",
                fadeOut: "fadeOut 0.2s ease-in-out",
                "dot-bounce": "dot-bounce 1.3s linear infinite",
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
