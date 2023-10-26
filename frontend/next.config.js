/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
        return [
            {
                source: '/api/:path*',
                destination: 'http://backend:8080/:path*',
            },
        ];
    },
    output: 'standalone',
};

module.exports = nextConfig
