package com.robert_gym.gym_manager.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ClassDto(
        Specialization classSpecialization, List<SubscriptionType> allowedSubscriptions,
        TrainerDto trainer, List<TraineeDto> trainees, Integer length, LocalDateTime startDate, LocalDateTime endDate
) {
}
