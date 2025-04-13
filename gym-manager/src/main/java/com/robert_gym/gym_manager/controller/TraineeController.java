package com.robert_gym.gym_manager.controller;

import com.robert_gym.gym_manager.dto.TraineeDto;
import com.robert_gym.gym_manager.entity.Trainee;
import com.robert_gym.gym_manager.service.TraineeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @Autowired
    private TraineeService traineeService;

    @GetMapping
    public Flux<TraineeDto> getTrainees() {
        logger.info("Retrieving all the trainees");
        return traineeService.getTrainees();
    }

    @PostMapping("/enroll")
    public Mono<ResponseEntity<Trainee>> enrollTrainee(@Valid @RequestBody TraineeDto trainee) {
        logger.info("Trainee who was just enroled {}", trainee);
        return traineeService.createTrainee(trainee).map(ResponseEntity::ok);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<String>> updateTrainee(@PathVariable("id") Long traineeId, @RequestBody TraineeDto updateTraineeRequest) {
        logger.info("Trying to update trainee with id {}", traineeId);
        return traineeService.findById(traineeId)
                .flatMap(trainee -> traineeService.updateTrainee(trainee, updateTraineeRequest)
                        .map(m -> ResponseEntity.ok("Trainee was successfully updated")))
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body("There is no trainee with the provided id")));
    }

    @PostMapping("/attend/{classId}/user/{userId}")
    public Mono<String> attendClass(@PathVariable("classId") String classId, @PathVariable String userId) {
        logger.info("User with id {} will attend class with id {}", userId, classId);
        return Mono.just("User is successfully assigned to this class");
    }
}
