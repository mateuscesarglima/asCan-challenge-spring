package com.academy.challenge.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

  public Subscription updateSubscription(UUID id, Subscription subscription) {
    Subscription newObj = subscriptionRepository.getReferenceById(id);
    updateData(newObj, subscription);
    return subscriptionRepository.save(newObj);
  }

  private void updateData(Subscription newObj, Subscription subscription) {
    newObj.setStatus(subscription.getStatus());
    newObj.setUpdated_at(LocalDateTime.now(ZoneId.of("UTC")));
  }
}
