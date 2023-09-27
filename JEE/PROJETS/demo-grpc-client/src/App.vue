<template>
  <main>

    <input v-model="message" />
    <button @click="sendMessage">Envoyer</button>
  </main>
</template>

<script setup>
import { ref } from 'vue';
import { HelloServiceClient } from './proto/HelloService.client';
import { HelloRequest } from './proto/HelloService';
import { GrpcWebFetchTransport } from '@protobuf-ts/grpcweb-transport';

const message = ref('test');

const client = new HelloServiceClient(
  new GrpcWebFetchTransport({
    baseUrl: 'http://127.0.0.1:9000'
  })
);

function sendMessage() {
  let request = HelloRequest.fromJson({
    username: "Titi",
    message: message.value
  });

  client.hello(request).then((res) => {
    console.log(res);
  });
}
</script>