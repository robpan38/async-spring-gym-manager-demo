package com.robert_gym.gym_manager.repository;

import com.robert_gym.gym_manager.entity.Trainee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends R2dbcRepository<Trainee, Long> {

}
