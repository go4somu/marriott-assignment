package com.ps.publishService.router;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


import com.ps.publishService.handlers.PublishHandler;
import com.ps.common.models.AttendanceEvent;

@Component
public class PublishServiceRouter {

    @Bean
    public RouterFunction<ServerResponse> routeData(PublishHandler handler) {
        return route(POST("/api/data")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ok().contentType(MediaType.APPLICATION_JSON)
                        .body(fromPublisher(handler.handleData(request.bodyToMono(AttendanceEvent.class)), AttendanceEvent.class))
        );
    }
}
