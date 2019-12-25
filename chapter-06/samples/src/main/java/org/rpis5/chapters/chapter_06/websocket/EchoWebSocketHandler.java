package org.rpis5.chapters.chapter_06.websocket;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class EchoWebSocketHandler implements WebSocketHandler {             // (1)

    @NotNull
    @Override                                                               // (2)
    public Mono<Void> handle(@NotNull WebSocketSession session) {           //
        return session                                                      // (3)
                .receive()                                                  // (4)
                .map(WebSocketMessage::getPayloadAsText)                    // (5)
                .map(tm -> "Echo: " + tm)                                   // (6)
                .doOnNext(tm -> System.out.println("<<<: " + tm))
                .map(session::textMessage)                                  // (7)
                .as(session::send);                                         // (8)
    }                                                                       //
}
