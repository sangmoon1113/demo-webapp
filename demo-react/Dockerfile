# syntax=docker/dockerfile:1
FROM node:lts-alpine AS builder
WORKDIR /src
COPY package.json .
RUN npm install

COPY . .
RUN npm run build


FROM nginx:alpine-slim
RUN rm /etc/nginx/conf.d/default.conf
RUN rm -rf /etc/nginx/conf.d/*
#COPY ./nginx.conf /etc/nginx/conf.d/
#RUN echo "server {\n" \
#         "    listen 80;\n" \
#         "    location / {\n" \
#         "        root /usr/share/nginx/html;\n" \
#         "        index index.html;\n" \
#         "        try_files $uri /index.html;\n" \
#         "    }\n" \
#         "}\n" > /etc/nginx/conf.d/nginx.conf
COPY <<EOF /etc/nginx/conf.d/nginx.conf
server {
    listen 80;
    
    #access_log off;
    #error_log off;
    
    location ^~ /swagger-ui {
        proxy_http_version 1.1;
        proxy_pass  http://backend:8080;
    }
    
    location ^~ /v3 {
        proxy_http_version 1.1;
        proxy_pass  http://backend:8080;
    }
    
    location ^~ /api {
        proxy_http_version 1.1;
        proxy_pass  http://backend:8080;
    }
    
    location / {
        root /usr/share/nginx/html;
        index index.html;
        try_files \$uri /index.html;
    }
}
EOF

COPY --from=builder /src/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

# docker build -t demo-react .
# docker run --name demo-react -p 80:80 demo-react