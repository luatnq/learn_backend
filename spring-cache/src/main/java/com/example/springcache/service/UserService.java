package com.example.springcache.service;

import com.example.springcache.data.entity.User;

import java.util.List;

public interface UserService {
  void addUser(String name, int age);

  List<User> list();
}
