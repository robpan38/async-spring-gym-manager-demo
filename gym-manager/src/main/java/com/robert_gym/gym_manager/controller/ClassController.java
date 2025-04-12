package com.robert_gym.gym_manager.controller;

import com.robert_gym.gym_manager.dto.ClassDto;
import com.robert_gym.gym_manager.dto.Specialization;
import com.robert_gym.gym_manager.dto.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    Logger logger = LoggerFactory.getLogger(ClassController.class);

    @GetMapping
    public Flux<ClassDto> getClasses() {
        return Flux.just(
                new ClassDto(Specialization.CARDIO, List.of(SubscriptionType.BRONZE), null, List.of(), 2, LocalDateTime.now().minusHours(2), LocalDateTime.now()),
                new ClassDto(Specialization.STRENGTH, List.of(SubscriptionType.BRONZE), null, List.of(), 2, LocalDateTime.now().minusHours(2), LocalDateTime.now()),
                new ClassDto(Specialization.CYCLING, List.of(SubscriptionType.BRONZE), null, List.of(), 2, LocalDateTime.now().minusHours(2), LocalDateTime.now())
        );
    }

    @PostMapping("/create")
    public Mono<ClassDto> createClass(@RequestBody ClassDto classDto) {
        logger.info("Creating class {}", classDto);
        return Mono.just(classDto);
    }

    @PostMapping("/{classId}/assignTrainer/{trainerId}")
    public Mono<ClassDto> assignTrainer(@PathVariable("classId") String classId, @PathVariable("trainerId") String trainerId) {
        logger.info("Class with id {} is assigned to the trainer with id {}", classId, trainerId);
        return Mono.just(new ClassDto(Specialization.CARDIO, List.of(SubscriptionType.BRONZE), null, List.of(), 2, LocalDateTime.now().minusHours(2), LocalDateTime.now()));
    }

    @PostMapping("/{classId}/enrollTrainee/{traineeId}")
    public Mono<ClassDto> enrollTrainee(@PathVariable("classId") String classId, @PathVariable("traineeId") String traineeId) {
        logger.info("Trainee with id {} just enrolled to the class with id {}", traineeId, classId);
        return Mono.just(new ClassDto(Specialization.CARDIO, List.of(SubscriptionType.BRONZE), null, List.of(), 2, LocalDateTime.now().minusHours(2), LocalDateTime.now()));
    }
}
