package com.example.authentication.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AuthenticationControllerTest {

  @GetMapping("/authorities")
//  @PreAuthorize("hasAuthority('SCOPE_profile.read')")
  public Map<String,Object> getPrincipalInfo(JwtAuthenticationToken principal) {
    Collection<String> authorities = principal.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    Map<String,Object> info = new HashMap<>();
    info.put("name", principal.getName());
    info.put("authorities", authorities);
    info.put("tokenAttributes", principal.getTokenAttributes().get("preferred_username"));

    return info;
  }
}
