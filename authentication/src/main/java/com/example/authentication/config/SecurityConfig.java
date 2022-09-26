package com.example.authentication.config;


import com.example.authentication.converter.role.JwtKeycloakRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().and()
        .authorizeRequests(authorize
            -> authorize.antMatchers("/users/**").hasAnyRole("user").anyRequest().authenticated())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .oauth2ResourceServer().jwt()
        .jwtAuthenticationConverter(customJwtAuthenticationConverter());
  }


  @Bean
  public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
    JwtKeycloakRoleConverter converter = new JwtKeycloakRoleConverter();
    return converter;
  }

  @Bean
  public JwtAuthenticationConverter customJwtAuthenticationConverter() {

    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());

    return converter;
  }
}
