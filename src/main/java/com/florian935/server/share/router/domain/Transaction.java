package com.florian935.server.share.router.domain;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {

    private String id;
    private Instant instant;
    private float price;
}
