package com.academy.challenge.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.challenge.dto.SubscriptionDto;
import com.academy.challenge.entities.EventHistory;
import com.academy.challenge.entities.Status;
import com.academy.challenge.entities.Subscription;
import com.academy.challenge.repositories.EventHistoryRepository;
import com.academy.challenge.repositories.SubscriptionRepository;

@Service
public class SubscriptionServices {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private EventHistoryRepository eventHistoryRepository;

  @Transactional
  @RabbitListener(queues = "subscription.v1.subscription-status")
  public void createSubscription(SubscriptionDto subscriptionDto) {
    var subscription = new Subscription();
    BeanUtils.copyProperties(subscriptionDto, subscription);
    subscription.setCreated_at(LocalDateTime.now());
    subscription.getUser().setCreated_at(LocalDateTime.now());
    subscription.getStatus().setStatus_name("ACTIVE");
    var eventHistory = new EventHistory();
    eventHistory.setCreated_at(LocalDateTime.now());
    eventHistory.setType("SUBSCRIPTION_PURCHASED");
    eventHistory.setSubscription(subscription);
    eventHistoryRepository.save(eventHistory);
    subscriptionRepository.save(subscription);
  }

  public List<Subscription> findAll() {
    return subscriptionRepository.findAll();
  }

  public Optional<Subscription> findById(UUID id) {
    return subscriptionRepository.findById(id);
  }

  @RabbitListener(queues = "subscription.v1.subscription-status-update")
  public void updateSubscription(SubscriptionDto subscriptionDto) {
    var subscription = new Subscription();
    BeanUtils.copyProperties(subscriptionDto, subscription);
    Subscription newObj = subscriptionRepository.findByUserName(subscription.getUser().getName());
    updateData(newObj, subscription);
    if (subscription.getStatus().getStatus_name().equals("CANCELED")) {
      var eventHistory = new EventHistory();
      eventHistory.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
      eventHistory.setType("SUBSCRIPTION_CANCELED");
      eventHistory.setSubscription(newObj);
      eventHistoryRepository.save(eventHistory);
      subscriptionRepository.save(newObj);
    } else {
      var eventHistory = new EventHistory();
      eventHistory.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
      eventHistory.setType("SUBSCRIPTION_RESTARTED");
      eventHistory.setSubscription(newObj);
      eventHistoryRepository.save(eventHistory);
      subscriptionRepository.save(newObj);
    }
  }

  private void updateData(Subscription newObj, Subscription subscription) {
    Status status = new Status();
    status.setStatus_name(subscription.getStatus().getStatus_name());
    newObj.setStatus(status);
    newObj.setUpdated_at(LocalDateTime.now(ZoneId.of("UTC")));
  }
}
