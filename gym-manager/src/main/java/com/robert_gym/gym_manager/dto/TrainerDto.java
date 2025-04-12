package com.robert_gym.gym_manager.dto;

import java.util.List;

public record TrainerDto(String firstName, String lastName, List<Specialization> specializations) {
}
