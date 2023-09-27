# demo-grpc-client

This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

### Compile Protobuf

```sh
npm install protoc -g

npx protoc --ts_out src/proto --ts_opt long_type_string --proto_path proto proto/HelloService.proto 

```

### Utiliser un proxy pour HTTP/2 (les navigateurs ne le supportant pas (encore))

```sh
docker run -d -v ./envoy.yaml:/etc/envoy/envoy.yaml --name envoy -p 9000:8080 envoyproxy/envoy-dev
```

