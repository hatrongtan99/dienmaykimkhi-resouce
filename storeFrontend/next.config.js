/** @type {import('next').NextConfig} */
const nextConfig = {
    experimental: {
        appDir: true,
    },
    output: "standalone",
    images: {
        remotePatterns: [
            {
                protocol: "http",
                hostname: "localhost",
            },
            {
                protocol: "https",
                hostname: "maydochuyendung.com",
            },
        ],
    },
    reactStrictMode: false,
};

module.exports = nextConfig;
