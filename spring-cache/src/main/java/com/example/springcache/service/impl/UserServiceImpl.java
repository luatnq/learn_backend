package com.example.springcache.service.impl;

import com.example.springcache.data.entity.User;
import com.example.springcache.repository.UserRepository;
import com.example.springcache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;


  public void addUser(String name, int age){
    repository.save(new User(name, age));
  }

  @Override
  public List<User> list() {
    return repository.findAll();
  }


}
