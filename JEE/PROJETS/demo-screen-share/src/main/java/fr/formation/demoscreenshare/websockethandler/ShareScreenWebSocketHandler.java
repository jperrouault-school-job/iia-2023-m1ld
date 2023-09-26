package fr.formation.demoscreenshare.websockethandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class ShareScreenWebSocketHandler implements WebSocketHandler {
	private final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());
	private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        final String sessionId = session.getId();

        if (sessionIds.add(sessionId)) {
            sessions.add(session);

            log.debug("Session WebSocket commencée [{}]", sessionId);
            
            Flux<WebSocketMessage> shareStreaming = session.receive().doFinally(sig -> {
                log.debug("Session WebSocket terminée par le client [{}], [{}]", sig.name(), sessionId);
                session.close();

                sessionIds.remove(sessionId);
                sessions.remove(session);
            })
            .map(WebSocketMessage::getPayloadAsText)
            .map(base64 -> {
                log.debug("Données reçues par le client [{}]", sessionId);
                
                sessions.forEach(s -> {
                    if (s != session) {
                        s.send(Mono.just(s.textMessage(base64))).subscribe();
                    }
                });

                return session.textMessage(base64);
            });

            return session.send(shareStreaming);
        }

        return Mono.empty();
    }
}
