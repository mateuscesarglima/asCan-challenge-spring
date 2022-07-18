package com.academy.challenge.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_event_history")
public class EventHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "subscription_id")
  private Subscription subscription;

  private String type;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime created_at;

}
