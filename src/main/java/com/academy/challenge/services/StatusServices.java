package com.academy.challenge.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.challenge.entities.Status;
import com.academy.challenge.repositories.StatusRepository;

@Service
public class StatusServices {

  @Autowired
  private StatusRepository statusRepository;

  public Object saveStatus(Status status) {
    return statusRepository.save(status);
  }

  public List<Status> findAll() {
    return statusRepository.findAll();
  }

  public Optional<Status> findById(UUID id) {
    return statusRepository.findById(id);
  }

}
