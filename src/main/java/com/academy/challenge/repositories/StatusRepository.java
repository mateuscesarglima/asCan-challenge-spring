package com.academy.challenge.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.challenge.entities.Status;

public interface StatusRepository extends JpaRepository<Status, UUID> {

}
