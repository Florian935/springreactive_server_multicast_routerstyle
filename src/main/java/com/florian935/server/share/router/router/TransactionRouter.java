package com.florian935.server.share.router.router;

import com.florian935.server.share.router.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TransactionRouter {

    @Bean
    public RouterFunction<ServerResponse> transactionRoutes(
            TransactionHandler transactionHandler) {
        return nest(
                path("/transaction"),
                route()
                        .GET("", transactionHandler::streamTransaction)
                        .GET("/SSE", transactionHandler::streamSSETransaction)
                        .build()
        );
    }
}
