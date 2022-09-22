package com.example.springcache.controller;


import com.example.springcache.data.entity.User;
import com.example.springcache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public void addUser(@RequestParam(name = "name") String name,
                      @RequestParam(name = "age") int age
  ) {
    userService.addUser(name, age);

  }

  @GetMapping
  public List<User> list() {
    return userService.list();
  }

}
