package com.academy.challenge.dto;

import com.academy.challenge.entities.Subscription;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventHistoryDto {

  private Subscription subscription;

  private String type;

}
