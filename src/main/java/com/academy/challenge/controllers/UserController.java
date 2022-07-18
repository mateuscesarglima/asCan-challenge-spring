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

import com.academy.challenge.dto.UserDto;
import com.academy.challenge.entities.User;
import com.academy.challenge.services.UserServices;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  private UserServices UserServices;

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto) {
    var user = new User();
    BeanUtils.copyProperties(userDto, user);
    user.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
    return ResponseEntity.status(HttpStatus.CREATED).body(UserServices.saveUser(user));
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(UserServices.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
    Optional<User> user = UserServices.findById(id);
    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(user.get());
  }
}
