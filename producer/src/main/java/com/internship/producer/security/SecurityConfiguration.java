package com.internship.producer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.internship.producer.controller.ProductControllerConstants.ADD_PRODUCT_URL;
import static com.internship.producer.controller.ProductControllerConstants.MAPPING_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(MAPPING_URL+ADD_PRODUCT_URL).hasRole(UserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("tonya")
                .password(passwordEncoder().encode("1234"))
                .roles(UserRole.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                user
        );
    }
}
