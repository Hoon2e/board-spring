package com.hoon.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
import com.hoon.article.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Validated
public class UserController {
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseDto<Long>> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
		Long id= userService.registerUser(userCreateDto);
		ResponseDto res = ResponseDto.success(id, HttpStatus.CREATED);
		return ResponseEntity.status(res.getStatusCode()).body(res);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseDto<UserResponseDto>> loginUser(@RequestBody UserLoginDto userLoginDto)
	{
		UserResponseDto userResponseDto = userService.login(userLoginDto);
		ResponseDto<UserResponseDto> res = ResponseDto.success(userResponseDto);
		return ResponseEntity.status(res.getStatusCode()).body(res);
	}
}
