package com.ps.queryService.routers;

import com.ps.queryService.entities.AttendanceEventEntity;
import com.ps.queryService.handlers.AttendanceEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class AttendanceEventRouter {
    private static final Logger logger = LoggerFactory.getLogger(AttendanceEventRouter.class);
	@Autowired
	private AttendanceEventHandler handler;
	
	@Bean
    public RouterFunction<ServerResponse> attendanceEventRoutes() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return RouterFunctions.route()
                .GET("/api/attendance-events/{employeeId}",
                        request -> {
                            Flux<AttendanceEventEntity> attendanceEvents = handler.getAttendanceEventsByEmployeeId
                                    (request.pathVariable("employeeId"));

                            return ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(attendanceEvents, AttendanceEventEntity.class);
                        })
                .GET("/api/getDayReport/{employeeId}/{day}",
                        request -> {
                            Flux<Map<String,Long>> resultFlux = null;
                            try {
                                Date startDate = dateFormat.parse(request.pathVariable("day") + " 00:00:00");
                                Date endDate = dateFormat.parse(request.pathVariable("day") + " 23:59:59");
                                Flux<AttendanceEventEntity> attendanceEvents = handler.getAttendanceEventsByEmployeeId((request.pathVariable("employeeId")));
                                Flux<AttendanceEventEntity>  filteredFlux =
                                    attendanceEvents
                                        .filter(event -> {
                                            LocalDate eventDate = event.getTimestamp().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
                                            LocalDate targetDate = LocalDate.parse(request.pathVariable("day")); // Replace with your target date
                                            return eventDate.isEqual(targetDate);
                                        });
                                //filteredFlux.
                                //       doOnNext(item -> System.out.println("Received item: " + item))
                                //        .subscribe();
                                Mono<AttendanceEventEntity> firstSwipeInMono = filteredFlux
                                        .filter(event -> "SWIPE_IN".equals(event.getEventType()))
                                        .next(); // Get the first occurrence of SWIPE_IN

                                Mono<AttendanceEventEntity> lastSwipeOutMono = filteredFlux
                                        .filter(event -> "SWIPE_OUT".equals(event.getEventType()))
                                        .reduce((first, second) -> second); // Get the last occurrence of SWIPE_OUT
                                /*
                                firstSwipeInMono.
                                        doOnNext(item -> logger.info("Received item: " + item))
                                        .subscribe();
                                lastSwipeOutMono.
                                        doOnNext(item -> logger.info("Received item: " + item))
                                        .subscribe();
                                */

                                Mono<Duration> durationMono = Mono.zip(firstSwipeInMono, lastSwipeOutMono)
                                        .map(tuple -> {
                                            Date firstTimestamp = tuple.getT1().getTimestamp();
                                            Date lastTimestamp = tuple.getT2().getTimestamp();
                                            long durationMillis = lastTimestamp.getTime() - firstTimestamp.getTime();
                                            return Duration.ofMillis(durationMillis);
                                        });
                                Mono<Map<String,Long>> hoursMono = durationMono.map(hours -> {
                                    Map<String, Long> result = new HashMap<>();
                                    result.put("hours",hours.toHours());
                                    return result;
                                });
                                resultFlux = Flux.concat(hoursMono);

                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }

                            return ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(resultFlux, AttendanceEventEntity.class);
                        })
                .build();
    }

}
