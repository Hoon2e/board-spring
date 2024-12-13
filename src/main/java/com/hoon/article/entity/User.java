package com.hoon.article.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Long userId;
	private String username;
	private String password;
	private String email;
	private String role;
	private LocalDateTime createdAt;
	private boolean enabled;
}
