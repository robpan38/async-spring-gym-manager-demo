package com.robert_gym.gym_manager.service;

import com.robert_gym.gym_manager.controller.TraineeController;
import com.robert_gym.gym_manager.dto.TraineeDto;
import com.robert_gym.gym_manager.entity.Trainee;
import com.robert_gym.gym_manager.repository.TraineeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TraineeService {

    Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @Autowired
    TraineeRepository traineeRepository;

    public Flux<TraineeDto> getTrainees() {
        return traineeRepository
                .findAll()
                .map(this::convertToDto);
    }

    public Mono<Trainee> createTrainee(TraineeDto traineeDto) {
        Trainee traineeEntity = convertToEntity(traineeDto);
        return traineeRepository.save(traineeEntity);
    }

    public Mono<Trainee> updateTrainee(Trainee trainee, TraineeDto updateRequest) {
        if (updateRequest.firstName() != null) {
            trainee.setFirstName(updateRequest.firstName());
        }
        if (updateRequest.lastName() != null) {
            trainee.setLastName(updateRequest.lastName());
        }
        if (updateRequest.subscriptionType() != null) {
            trainee.setSubscriptionType(updateRequest.subscriptionType());
        }
        if (updateRequest.expirationDate() != null) {
            trainee.setExpirationDate(updateRequest.expirationDate());
        }
        return traineeRepository.save(trainee);
    }

    public Mono<Trainee> findById(Long id) {
        return traineeRepository.findById(id);
    }

    @Bean
    public Disposable removeExpiredTrainees() {
        return Flux.interval(Duration.ofMinutes(1))
                .publishOn(Schedulers.boundedElastic())
                .onBackpressureDrop()
                .concatMap(__ ->
                        traineeRepository.findAll()
                                .flatMap(trainee -> {
                                    if (LocalDateTime.now().isAfter(trainee.getExpirationDate())) {
                                        logger.info("Trainee with id {} has an expired subscription, will be deleted", trainee.getId());
                                        return traineeRepository.delete(trainee);
                                    }
                                    return Mono.empty();
                                }).then(), 0)
                .subscribe();
    }

    private Trainee convertToEntity(TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
        trainee.setFirstName(traineeDto.firstName());
        trainee.setLastName(traineeDto.lastName());
        trainee.setSubscriptionType(traineeDto.subscriptionType());
        trainee.setExpirationDate(traineeDto.expirationDate());
        return trainee;
    }

    private TraineeDto convertToDto(Trainee trainee) {
        return new TraineeDto(
                trainee.getFirstName(), trainee.getLastName(),
                trainee.getSubscriptionType(), trainee.getExpirationDate()
        );
    }

}
