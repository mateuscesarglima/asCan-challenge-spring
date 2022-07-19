package com.academy.challenge.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
  private SubscriptionServices subscriptionServices;

  @Autowired
  RabbitTemplate rabbitTemplate;

  @PostMapping
  public ResponseEntity<String> createSubscription(@RequestBody @Valid SubscriptionDto subscriptiondDto) {

    String queue = "subscription.v1.subscription-status";
    String queueUpdate = "subscription.v1.subscription-status-update";

    ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    if (subscriptiondDto.getStatus().getStatus_name().equals("PURSCHASED")) {
      rabbitTemplate.convertAndSend(queue, subscriptiondDto);
      response = ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully");

    } else if (subscriptiondDto.getStatus().getStatus_name().equals("CANCELED")) {
      Subscription obj = subscriptionServices.findByUserName(subscriptiondDto.getUser().getName());
      if (obj != null) {
        rabbitTemplate.convertAndSend(queueUpdate, subscriptiondDto);
        response = ResponseEntity.status(HttpStatus.CREATED).body("Subscription updated successfully");
      } else {
        response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Status cannot be ''canceled'' because user does not exist.");
      }
    } else if (subscriptiondDto.getStatus().getStatus_name().equals("RESTARTED")) {
      Subscription obj = subscriptionServices.findByUserName(subscriptiondDto.getUser().getName());
      if (obj.getStatus().getStatus_name().equals("ACTIVE")) {
        response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("User status cannot be reset because your current status is not cancelled.");
      } else {
        rabbitTemplate.convertAndSend(queueUpdate, subscriptiondDto);
        response = ResponseEntity.status(HttpStatus.CREATED).body("Subscription updated successfully");
      }
    } else {

      response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid subscription option inserted");

    }
    return response;
  }

  @GetMapping
  public ResponseEntity<List<Subscription>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(subscriptionServices.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
    Optional<Subscription> subscription = subscriptionServices.findById(id);
    if (!subscription.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription Not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(subscription.get());
  }
}