package com.hoon.article.dto.user;

import com.hoon.article.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
	private Long userId;
	private String username;
	private String email;
	private String role;
	
	public static UserResponseDto FromEntity(User user) {
		return UserResponseDto
				.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.role(user.getRole())
				.build();
	}
}
