package com.robert_gym.gym_manager.controller;

import com.robert_gym.gym_manager.dto.Specialization;
import com.robert_gym.gym_manager.dto.SubscriptionType;
import com.robert_gym.gym_manager.dto.TrainerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @GetMapping
    public Flux<TrainerDto> getTrainees() {
        logger.info("Retrieving all the trainers");
        return Flux.just(
                new TrainerDto("test", "test", List.of(Specialization.STRENGTH)),
                new TrainerDto("test2", "test2", List.of(Specialization.STRENGTH)),
                new TrainerDto("test3", "test3", List.of(Specialization.STRENGTH))
        );
    }

    @PostMapping("/hire")
    public Mono<TrainerDto> hireTrainer(@RequestBody TrainerDto trainer) {
        logger.info("Hiring trainer {}", trainer);
        return Mono.just(trainer);
    }

    @PutMapping("/update/{id}")
    public Mono<TrainerDto> updateTrainer(@PathVariable("id") String trainerId, @RequestBody TrainerDto trainer) {
        logger.info("Updating trainer with id {} with info {}", trainerId, trainer);
        return Mono.just(trainer);
    }

    @DeleteMapping("/quit/{id}")
    public Mono<String> quitTraining(@PathVariable("id") String trainerId) {
        return Mono.just("Trainer " + trainerId + " is no longer teaching classes at this gym");
    }
}
