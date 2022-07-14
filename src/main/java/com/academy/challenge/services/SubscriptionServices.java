package com.academy.challenge.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.challenge.entities.Subscription;
import com.academy.challenge.repositories.SubscriptionRepository;

@Service
public class SubscriptionServices {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Transactional
  public Object createSubscription(Subscription subscription) {
    return subscriptionRepository.save(subscription);
  }

  public List<Subscription> findAll() {
    return subscriptionRepository.findAll();
  }

  public Optional<Subscription> findById(UUID id) {
    return subscriptionRepository.findById(id);
  }

}
