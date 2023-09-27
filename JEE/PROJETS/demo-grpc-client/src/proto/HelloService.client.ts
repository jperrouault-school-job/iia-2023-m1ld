// @generated by protobuf-ts 2.9.1 with parameter long_type_string
// @generated from protobuf file "HelloService.proto" (package "fr.formation.demogrpcserver.proto", syntax proto3)
// tslint:disable
import type { RpcTransport } from "@protobuf-ts/runtime-rpc";
import type { ServiceInfo } from "@protobuf-ts/runtime-rpc";
import { HelloService } from "./HelloService";
import { stackIntercept } from "@protobuf-ts/runtime-rpc";
import type { HelloResponse } from "./HelloService";
import type { HelloRequest } from "./HelloService";
import type { ServerStreamingCall } from "@protobuf-ts/runtime-rpc";
import type { RpcOptions } from "@protobuf-ts/runtime-rpc";
/**
 * @generated from protobuf service fr.formation.demogrpcserver.proto.HelloService
 */
export interface IHelloServiceClient {
    /**
     * @generated from protobuf rpc: hello(fr.formation.demogrpcserver.proto.HelloRequest) returns (stream fr.formation.demogrpcserver.proto.HelloResponse);
     */
    hello(input: HelloRequest, options?: RpcOptions): ServerStreamingCall<HelloRequest, HelloResponse>;
}
/**
 * @generated from protobuf service fr.formation.demogrpcserver.proto.HelloService
 */
export class HelloServiceClient implements IHelloServiceClient, ServiceInfo {
    typeName = HelloService.typeName;
    methods = HelloService.methods;
    options = HelloService.options;
    constructor(private readonly _transport: RpcTransport) {
    }
    /**
     * @generated from protobuf rpc: hello(fr.formation.demogrpcserver.proto.HelloRequest) returns (stream fr.formation.demogrpcserver.proto.HelloResponse);
     */
    hello(input: HelloRequest, options?: RpcOptions): ServerStreamingCall<HelloRequest, HelloResponse> {
        const method = this.methods[0], opt = this._transport.mergeOptions(options);
        return stackIntercept<HelloRequest, HelloResponse>("serverStreaming", this._transport, method, opt, input);
    }
}