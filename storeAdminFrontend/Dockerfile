FROM node:alpine AS deps
WORKDIR /app
COPY package.json .
RUN npm install

FROM node:alpine AS builder
WORKDIR /app
COPY --from=deps /node_module .
COPY . .
RUN npm run build --production

# load env
FROM nginx:alpine AS runner
RUN apt add nodejs npm bash
COPY --from=builder /app .
# CMD ["/bin/bash", "-c", "npx react-inject-env set && cp -r /app/ && nginx -g 'deamon off;'"]
