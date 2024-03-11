package com.security.config.security;

import com.security.exceptions.ObjectNotFoundException;
import com.security.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInjector {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
        authenticationStrategy.setPasswordEncoder(passwordEncoder());
        authenticationStrategy.setUserDetailsService(userDetailsService());
        return authenticationStrategy;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // TODO: create user detail here
    @Bean
    public UserDetailsService userDetailsService(){
        return (username) ->{
            return userRepository.findByUsername(username).orElseThrow(
                    ()-> new ObjectNotFoundException(String.format("User not found with username %s",username))
            );
        };
    }
}
