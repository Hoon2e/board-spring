package com.hoon.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

import com.hoon.article.repository.UserRepository;
import com.hoon.article.security.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Profile("!test")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CorsConfig corsConfig;
	private final UserRepository userRepository;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/api/user/**").permitAll()
//				.anyRequest().authenticated()
				.anyRequest().permitAll()
				);
		http.rememberMe(remember -> remember
				.rememberMeServices(rememberMeServices())
				);
		http.cors(cors -> cors
				.configurationSource(corsConfig.corsConfigurationSource()));
		return http.build();
	}
	
	@Bean
	public RememberMeServices rememberMeServices() {
	    TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("myAppKey", userDetailsService());
	    rememberMeServices.setTokenValiditySeconds(604800); // 1주일
	    rememberMeServices.setAlwaysRemember(true);
	    return rememberMeServices;
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService(userRepository);
	}

	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(daoAuthenticationProvider())
		 .build();
    }
}
