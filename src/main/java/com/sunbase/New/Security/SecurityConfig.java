package com.sunbase.New.Security;

import com.sunbase.New.Repository.UserInfoRepository;
import com.sunbase.New.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserInfoRepository userRepository;

    @Autowired
    public SecurityConfig(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(userRepository, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET, "*/api/customers/welcome*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/customers/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/customers/addNewUser").permitAll()
                                .requestMatchers(HttpMethod.POST, "*/api/customers/addCustomer*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/customers/deleteCustomer/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/customers/getCustomers").permitAll()
                                /*.requestMatchers(HttpMethod.POST, "/api/customers/register").permitAll()*/
                                .requestMatchers(HttpMethod.PUT, "/api/customers/updateCustomer").permitAll()
                                /*.requestMatchers("/api/customers/user/**").authenticated()
                                .requestMatchers("/api/customers/admin/**").authenticated()
                                .requestMatchers("/auth/admin/**").hasRole("ADMIN")*/
                )
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable);
                // For simplicity, disabling CSRF protection

        return http.build();
    }

    @Bean
    public JwtAuthFilter authFilter() {
        var registrationBean = new FilterRegistrationBean<JwtAuthFilter>();
        registrationBean.setFilter(new JwtAuthFilter());
        registrationBean.addUrlPatterns("/api/customers/*");
        return new JwtAuthFilter();
    }
}