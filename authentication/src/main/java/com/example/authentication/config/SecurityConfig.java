package com.example.authentication.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .cors().and()
        .authorizeRequests(authz
            -> authz.antMatchers("/api/users/*").hasAnyRole("user")
            .anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt());
  }
}
