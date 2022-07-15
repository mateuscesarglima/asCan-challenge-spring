package com.academy.challenge.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.challenge.entities.EventHistory;

public interface EventHistoryRepository extends JpaRepository<EventHistory, UUID> {

}
