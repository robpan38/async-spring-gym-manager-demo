package com.robert_gym.gym_manager.controller;

import com.robert_gym.gym_manager.dto.SubscriptionType;
import com.robert_gym.gym_manager.dto.TraineeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @GetMapping
    public Flux<TraineeDto> getTrainees() {
        logger.info("Retrieving all the trainees");
        return Flux.just(
                new TraineeDto("test", "test", SubscriptionType.BRONZE, LocalDateTime.now()),
                new TraineeDto("test2", "test2", SubscriptionType.BRONZE, LocalDateTime.now()),
                new TraineeDto("test3", "test3", SubscriptionType.BRONZE, LocalDateTime.now())
        );
    }

    @PostMapping("/enroll")
    public Mono<TraineeDto> enrollTrainee(@RequestBody TraineeDto trainee) {
        logger.info("Trainee who was just enroled {}", trainee);
        return Mono.just(trainee);
    }

    @PutMapping("/update/{id}")
    public Mono<TraineeDto> updateTrainee(@PathVariable("id") String traineeId, @RequestBody TraineeDto traineeDto) {
        logger.info("Applying update operation on trainee with id {}", traineeId);
        if (traineeDto.firstName() != null) {
            logger.info("Updating first name");
        }
        if (traineeDto.lastName() != null) {
            logger.info("Updating last name");
        }
        if (traineeDto.expirationDate() != null) {
            logger.info("Update expiration date");
        }
        if (traineeDto.subscriptionType() != null) {
            logger.info("Update subscription type");
        }
        return Mono.just(traineeDto);
    }

    @PostMapping("/attend/{classId}/user/{userId}")
    public Mono<String> attendClass(@PathVariable("classId") String classId, @PathVariable String userId) {
        logger.info("User with id {} will attend class with id {}", userId, classId);
        return Mono.just("User is successfully assigned to this class");
    }
}
