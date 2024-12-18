package com.hoon.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.article.dto.common.ResponseDto;
import com.hoon.article.dto.user.UserCreateDto;
import com.hoon.article.dto.user.UserLoginDto;
import com.hoon.article.dto.user.UserResponseDto;
import com.hoon.article.exception.AuthFailException;
import com.hoon.article.exception.ErrorCode;
import com.hoon.article.security.MyUserDetails;
import com.hoon.article.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Validated
public class UserController {
	private final UserService userService;
	private final SecurityContextRepository securityContextRepository;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto<Long>> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
		Long id= userService.registerUser(userCreateDto);
		ResponseDto res = ResponseDto.success(id, HttpStatus.CREATED);
		return ResponseEntity.status(res.getStatusCode()).body(res);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto<UserResponseDto>> loginUser(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response)
	{
		UserResponseDto userResponseDto = userService.login(userLoginDto, request, response);
		
		
		ResponseDto<UserResponseDto> res = ResponseDto.success(userResponseDto);
		
		return ResponseEntity.status(res.getStatusCode()).body(res);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ResponseDto<Void>> logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		ResponseDto<Void> res = ResponseDto.success(null, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/me")
	public ResponseEntity<ResponseDto<UserResponseDto>> me(@AuthenticationPrincipal MyUserDetails myUserDetails) {
		if(myUserDetails == null)
		{
			throw new AuthFailException();
		}
		UserResponseDto userResponseDto = UserResponseDto.FromEntity(myUserDetails.getUser());
		ResponseDto<UserResponseDto> res = ResponseDto.success(userResponseDto);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
