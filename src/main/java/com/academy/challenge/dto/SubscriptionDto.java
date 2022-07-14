package com.academy.challenge.dto;

import com.academy.challenge.entities.Status;
import com.academy.challenge.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDto {

  private User user;

  private Status Status;

}
