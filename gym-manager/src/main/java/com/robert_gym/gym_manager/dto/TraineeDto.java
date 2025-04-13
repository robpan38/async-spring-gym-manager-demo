package com.robert_gym.gym_manager.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TraineeDto(
        @NotNull String firstName, @NotNull String lastName, @NotNull SubscriptionType subscriptionType, @NotNull LocalDateTime expirationDate) {
}
