package com.sunbase.New.Security;

import com.sunbase.New.Repository.UserInfoRepository;
import com.sunbase.New.Service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserInfoRepository userRepository;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(userRepository, passwordEncoder());
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                            authorizeRequests
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/welcome" )).permitAll()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/login")).permitAll()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/addNewUser")).permitAll()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/register")).permitAll()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/user/**")).authenticated()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/api/customers/admin/**")).authenticated()
                                    .dispatcherTypeMatchers(HttpMethod.valueOf("/auth/admin/**")).hasRole("ADMIN")
                                    // You may customize this based on your roles
                                    .anyRequest().authenticated();
                        }
                )
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthFilter authFilter() {
        return new JwtAuthFilter();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}