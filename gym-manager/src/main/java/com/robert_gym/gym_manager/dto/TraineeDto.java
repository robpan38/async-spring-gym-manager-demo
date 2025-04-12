package com.robert_gym.gym_manager.dto;

import java.time.LocalDateTime;

public record TraineeDto(String firstName, String lastName, SubscriptionType subscriptionType, LocalDateTime expirationDate) {
}
