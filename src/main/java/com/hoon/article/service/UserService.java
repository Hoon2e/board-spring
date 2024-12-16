package com.hoon.article.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
	}

	public UserResponseDto login(UserLoginDto loginDto, HttpServletRequest request) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginDto.getUsername(),
	                loginDto.getPassword()
	            )
	        );

	        // SecurityContext에 인증 정보 설정
	        SecurityContext context = SecurityContextHolder.getContext();
	        context.setAuthentication(authentication);

	        // SecurityContext를 세션에 저장
	        request.getSession(true).setAttribute(
	            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	            context
	        );

	        // 인증된 사용자 정보 반환
	        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
	        return UserResponseDto.FromEntity(myUserDetails.getUser());
	    } catch (AuthenticationException e) {
	        throw new LoginFailException();
	    }
	}

}
