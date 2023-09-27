package fr.formation.demogrpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class GrpcServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GrpcServerApplication.class, args);

        Server server = ServerBuilder
            .forPort(8082)
            .addService(new HelloServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
