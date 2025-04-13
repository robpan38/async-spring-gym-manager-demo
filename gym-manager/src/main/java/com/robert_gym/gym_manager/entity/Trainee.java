package com.robert_gym.gym_manager.entity;

import com.robert_gym.gym_manager.dto.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {

    @Id
    private Long id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("subscription_type")
    private SubscriptionType subscriptionType;
    @Column("expiration_date")
    private LocalDateTime expirationDate;
}
