package fr.formation.demoscreenshare.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import fr.formation.demoscreenshare.websockethandler.ShareScreenWebSocketHandler;

@Configuration
public class WebSocketConfig implements WebFluxConfigurer {
    @Bean
    HandlerMapping webSocketHandlerMapping(ShareScreenWebSocketHandler socketHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();

        map.put("/screening", socketHandler);

        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(map);
        
        return handlerMapping;
    }
}
