package com.academy.challenge.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.challenge.dto.EventHistoryDto;
import com.academy.challenge.entities.EventHistory;
import com.academy.challenge.services.EventHistoryServices;

@RestController
@RequestMapping(value = "/api/v1/event")
public class EventHistoryController {

  @Autowired
  private EventHistoryServices eventHistoryServices;

  @PostMapping
  public ResponseEntity<Object> createEventHistory(@RequestBody @Valid EventHistoryDto eventHistoryDto) {
    var eventHistory = new EventHistory();
    BeanUtils.copyProperties(eventHistoryDto, eventHistory);
    eventHistory.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
    return ResponseEntity.status(HttpStatus.CREATED).body(eventHistoryServices.saveEventHistory(eventHistory));
  }

  @GetMapping
  public ResponseEntity<List<EventHistory>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(eventHistoryServices.findAll());
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id,
      @RequestBody @Valid EventHistoryDto eventHistoryDto) {
    Optional<EventHistory> eventHistoryOptional = eventHistoryServices.findById(id);
    if (!eventHistoryOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(eventHistoryOptional.get());
  }

}
