FROM node:alpine AS deps
WORKDIR /app
COPY package*.json .
RUN npm install

FROM node:alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_module ./node_module
COPY . .
RUN npm run build

FROM node:alpine AS production
WORKDIR /app
COPY --from=builder /app/.next/standalone .
COPY --from=builder /app/.next/static ./.next/static

EXPOSE 3000
CMD ["node", "server.js"]