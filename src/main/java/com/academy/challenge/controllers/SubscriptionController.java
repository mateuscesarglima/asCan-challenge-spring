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

import com.academy.challenge.dto.SubscriptionDto;
import com.academy.challenge.entities.Subscription;
import com.academy.challenge.services.SubscriptionServices;

@RestController
@RequestMapping(value = "/api/v1/subscription")
public class SubscriptionController {

  @Autowired
  private SubscriptionServices SubscriptionServices;

  @PostMapping
  public ResponseEntity<Object> createSubscription(@RequestBody @Valid SubscriptionDto subscriptiondDto) {
    var subscription = new Subscription();
    BeanUtils.copyProperties(subscriptiondDto, subscription);
    subscription.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
    return ResponseEntity.status(HttpStatus.CREATED).body(SubscriptionServices.createSubscription(subscription));
  }

  @GetMapping
  public ResponseEntity<List<Subscription>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(SubscriptionServices.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
    Optional<Subscription> subscription = SubscriptionServices.findById(id);
    if (!subscription.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription Not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(subscription.get());
  }

}
