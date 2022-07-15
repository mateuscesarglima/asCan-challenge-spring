package com.academy.challenge.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.challenge.entities.EventHistory;
import com.academy.challenge.repositories.EventHistoryRepository;

@Service
public class EventHistoryServices {

  @Autowired
  private EventHistoryRepository eventHistoryRepository;

  public Object saveEventHistory(EventHistory eventHistory) {
    return eventHistoryRepository.save(eventHistory);
  }

  public List<EventHistory> findAll() {
    return eventHistoryRepository.findAll();
  }

  public Optional<EventHistory> findById(UUID id) {
    return eventHistoryRepository.findById(id);
  }

}
