package com.hoon.article.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoon.article.dto.user.UserCreateDto;
import com.hoon.article.dto.user.UserLoginDto;
import com.hoon.article.dto.user.UserResponseDto;
import com.hoon.article.dto.user.UserSearchCond;
import com.hoon.article.entity.User;
import com.hoon.article.exception.DuplicatUserException;
import com.hoon.article.exception.LoginFailException;
import com.hoon.article.repository.UserRepository;
import com.hoon.article.security.MyUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public Long registerUser(UserCreateDto userCreateDto) {
		boolean isExistUser = userRepository.isExistByUsernameOrEmail(userCreateDto.getUsername(),
				userCreateDto.getEmail());

		if (isExistUser) {
			throw new DuplicatUserException();
		}

		userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
		User user = userCreateDto.toEntity();

		userRepository.save(user);

		return user.getUserId();
	}

	public UserResponseDto login(UserLoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginDto.getUsername(), 
							loginDto.getPassword()
							)
					);
			MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
			UserResponseDto userResponseDto = UserResponseDto.FromEntity(myUserDetails.getUser());
			return userResponseDto;
		} catch (AuthenticationException e) {
			throw new LoginFailException();
		}
	}
}
