package com.academy.challenge.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.challenge.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

}
