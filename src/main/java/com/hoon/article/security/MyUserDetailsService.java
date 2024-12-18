package com.hoon.article.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hoon.article.entity.User;
import com.hoon.article.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository
		.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다: " + username));
		
		return new MyUserDetails(user);
	}
	
}
