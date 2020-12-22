package com.florian935.server.share.router.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionEvent {

    private long tickerNumber;
    private Transaction transaction;
}
