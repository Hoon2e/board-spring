package com.hoon.article.dto.user;

import com.hoon.article.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
	@NotBlank
    @Size(min = 4, max = 20, message = "아이디는 4글자에서 20글자 사이")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$", 
    message = "패스워드는 최소 8글자, 최소1개의 대문자, 특문이 필요합니다.")
    private String password;

    @NotBlank
    @Email(message = "이메일 형식이여야 합니다.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(USER|ADMIN)$", message = "권한은 USER 또는 ADMIN만 가능")
    private String role;

	public User toEntity() {
		return User.builder().username(username).password(password).email(email).role(role).build();
	}
}
