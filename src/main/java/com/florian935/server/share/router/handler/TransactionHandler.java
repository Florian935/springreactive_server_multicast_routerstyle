package com.florian935.server.share.router.handler;

import com.florian935.server.share.router.domain.TransactionEvent;
import com.florian935.server.share.router.service.TransactionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TransactionHandler {

    private final Flux<TransactionEvent> transactionStream;

    public TransactionHandler(TransactionService transactionService) {
        this.transactionStream = transactionService.transactionStream();
    }

    public Mono<ServerResponse> streamTransaction(final ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(transactionStream, TransactionEvent.class);
    }

    public Mono<ServerResponse> streamSSETransaction(final ServerRequest request) {
        return ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(transactionStream, TransactionEvent.class);
    }
}
