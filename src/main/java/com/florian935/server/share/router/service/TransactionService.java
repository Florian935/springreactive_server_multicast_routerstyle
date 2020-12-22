package com.florian935.server.share.router.service;

import com.florian935.server.share.router.domain.TransactionEvent;
import reactor.core.publisher.Flux;

public interface TransactionService {

    Flux<TransactionEvent> transactionStream();
}
