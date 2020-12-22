package com.florian935.server.share.router.service;

import com.florian935.server.share.router.domain.Transaction;
import com.florian935.server.share.router.domain.TransactionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * @author Florian935
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final long INTERVAL_IN_MILLIS = 1000;

    @Override
    public Flux<TransactionEvent> transactionStream() {
        return timerGenerator()
                .zipWith(transactionGenerator())
                .map(this::buildTransactionEvent)
                .share();
    }

    private TransactionEvent buildTransactionEvent(
            final Tuple2<Long, Transaction> tupleOfZip) {
        return TransactionEvent.builder()
                .tickerNumber(tupleOfZip.getT1())
                .transaction(tupleOfZip.getT2())
                .build();
    }

    private Flux<Long> timerGenerator() {
        return Flux.interval(
                        Duration.ofMillis(INTERVAL_IN_MILLIS)
        );
    }

    private Flux<Transaction> transactionGenerator() {
        return Flux.generate(
                () -> 0,
                (index, sink) -> {
                    sink.next(getRandomTransaction());
                    return ++index;
                }
        );
    }

    private Transaction getRandomTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .instant(Instant.now())
                .price(generateRandomFloatNumberBetween(1, 1000))
                .build();
    }

    private Float generateRandomFloatNumberBetween(int min, int max) {
        final float randomFloatNumber = (float) (Math.random() * (float) Math.random());
        return randomFloatNumber * (max - min + 1) + 1;
    }
}
