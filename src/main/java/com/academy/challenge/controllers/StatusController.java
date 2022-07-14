package com.academy.challenge.controllers;

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

import com.academy.challenge.dto.StatusDto;
import com.academy.challenge.entities.Status;
import com.academy.challenge.services.StatusServices;

@RestController
@RequestMapping(value = "/api/v1/status")
public class StatusController {

  @Autowired
  private StatusServices statusServices;

  @PostMapping
  public ResponseEntity<Object> createStatus(@RequestBody @Valid StatusDto statusDto) {
    var status = new Status();
    BeanUtils.copyProperties(statusDto, status);
    return ResponseEntity.status(HttpStatus.CREATED).body(statusServices.saveStatus(status));
  }

  @GetMapping
  public ResponseEntity<List<Status>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(statusServices.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
    Optional<Status> status = statusServices.findById(id);
    if (!status.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(status.get());
  }

}
