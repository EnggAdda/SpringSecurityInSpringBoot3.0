package com.example.springbootsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfiguration {


     @Bean
     public PasswordEncoder passwordEncoder(){
         return  new BCryptPasswordEncoder();
     }
     @Bean
     public UserDetailsService userDetailsService() {
    UserDetails userDetails  = User
            .withUsername("ravi")
            .password(passwordEncoder().encode("password"))
            .roles("user")
            .build();
    UserDetails adminDetails  = User
                 .withUsername("admin")
                 .password(passwordEncoder().encode("password"))
                 .roles("admin")
                 .build();

       return  new InMemoryUserDetailsManager(userDetails, adminDetails);

      }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

         httpSecurity.csrf().disable()
                 .authorizeHttpRequests()
                 .requestMatchers("/api/admin")
                 .hasRole("admin")
                 .requestMatchers("/api/user")
                 .hasRole("user")
                 .requestMatchers("/api/public")
                 .permitAll()
                 .anyRequest()
                 .authenticated()
                 .and()
                 .formLogin();
           return  httpSecurity.build();
    }
}
