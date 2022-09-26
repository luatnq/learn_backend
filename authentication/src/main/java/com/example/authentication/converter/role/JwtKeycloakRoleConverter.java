package com.example.authentication.converter.role;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtKeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(Jwt source) {
    List<String> roles = new ArrayList<>(getRealmRoles(source));

    return roles.stream()
        .map(roleName -> "ROLE_" + roleName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private List<String> getRealmRoles(Jwt jwt) {
    List <String> realmRoles = new ArrayList<>();

    Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
    if (realmAccess != null && !realmAccess.isEmpty()) {
      realmRoles = (List<String>) realmAccess.get("roles");
    }

    return realmRoles;
  }
}

