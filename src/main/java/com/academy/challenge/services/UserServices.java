package com.academy.challenge.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.challenge.entities.User;
import com.academy.challenge.repositories.UserRepository;

@Service
public class UserServices {

  @Autowired
  private UserRepository UserRepository;

  @Transactional
  public Object saveUser(User user) {
    return UserRepository.save(user);
  }

  public List<User> findAll() {
    return UserRepository.findAll();
  }

}
