FROM node:12.14.1
WORKDIR /angular-website
COPY package*.json ./
RUN npm install -g npm@6.13.4
RUN npm install -g @angular/cli@12.2.10
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4200
ENTRYPOINT ["ng", "serve", "--host", "0.0.0.0", "--disable-host-check"]
