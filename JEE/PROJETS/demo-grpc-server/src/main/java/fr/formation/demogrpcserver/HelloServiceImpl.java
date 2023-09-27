package fr.formation.demogrpcserver;

import fr.formation.demogrpcserver.proto.HelloRequest;
import fr.formation.demogrpcserver.proto.HelloResponse;
import fr.formation.demogrpcserver.proto.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        StringBuilder message = new StringBuilder()
                    .append("Hello, ")
                    .append(request.getUsername())
                    .append("! ")
                    .append("Ton message a bien été reçu [")
                    .append(request.getMessage())
                    .append("]");

        HelloResponse response = HelloResponse.newBuilder()
          .setMessage(message.toString())
          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted(); // Pour terminer le flux (ferme la connexion avec le client)
    }
}
