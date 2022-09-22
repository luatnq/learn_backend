package com.example.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

  @GetMapping("/users/hello")
  public ResponseEntity<String> hello(){
    return new ResponseEntity<>("HELlO", HttpStatus.OK);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> helloPublic(){
    return new ResponseEntity<>("HELlO", HttpStatus.OK);
  }
}
