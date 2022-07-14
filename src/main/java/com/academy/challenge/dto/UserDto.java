package com.academy.challenge.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  @NotBlank
  private String name;

}
