package com.robert_gym.gym_manager.service;

import com.robert_gym.gym_manager.dto.SubscriptionType;
import com.robert_gym.gym_manager.dto.TraineeDto;
import com.robert_gym.gym_manager.entity.Trainee;
import com.robert_gym.gym_manager.repository.TraineeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @InjectMocks
    private TraineeService traineeService;

    private final Trainee trainee = new Trainee(
            1L, "John", "Doe", SubscriptionType.GOLD, LocalDateTime.now().plusDays(30)
    );

    private final TraineeDto traineeDto = new TraineeDto(
            "John", "Doe", SubscriptionType.GOLD, LocalDateTime.now().plusDays(30)
    );

    @Test
    void getTrainees_shouldReturnAllTraineesAsDto() {
        when(traineeRepository.findAll()).thenReturn(Flux.just(trainee));

        StepVerifier.create(traineeService.getTrainees())
                .expectNextMatches(dto ->
                        dto.firstName().equals("John") && dto.lastName().equals("Doe"))
                .verifyComplete();
    }

    @Test
    void createTrainee_shouldSaveAndReturnTrainee() {
        when(traineeRepository.save(any())).thenReturn(Mono.just(trainee));

        StepVerifier.create(traineeService.createTrainee(traineeDto))
                .expectNextMatches(saved -> saved.getFirstName().equals("John"))
                .verifyComplete();
    }

    @Test
    void updateTrainee_shouldApplyUpdates() {
        Trainee updateTarget = new Trainee(
                1L, "Jane", "Smith", SubscriptionType.BRONZE, LocalDateTime.now().plusDays(10)
        );

        TraineeDto updateRequest = new TraineeDto(
                "John", "Doe", SubscriptionType.GOLD, LocalDateTime.now().plusDays(30)
        );

        when(traineeRepository.save(any())).thenReturn(Mono.just(trainee));

        StepVerifier.create(traineeService.updateTrainee(updateTarget, updateRequest))
                .expectNextMatches(updated -> updated.getFirstName().equals("John") &&
                        updated.getLastName().equals("Doe") &&
                        updated.getSubscriptionType().equals(SubscriptionType.GOLD))
                .verifyComplete();
    }

    @Test
    void findById_shouldReturnTraineeIfFound() {
        when(traineeRepository.findById(1L)).thenReturn(Mono.just(trainee));

        StepVerifier.create(traineeService.findById(1L))
                .expectNext(trainee)
                .verifyComplete();
    }

    @Test
    void findById_shouldReturnEmptyIfNotFound() {
        when(traineeRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(traineeService.findById(1L))
                .verifyComplete();
    }
}
