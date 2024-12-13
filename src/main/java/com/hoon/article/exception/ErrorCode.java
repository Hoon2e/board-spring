package com.hoon.article.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	NVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E1", "올바르지 않은 입력값입니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "E2", "잘못된 HTTP 메서드를 호출했습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E3", "서버 에러가 발생했습니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "E4", "존재하지 않는 엔티티입니다."),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "E5", "잘못된 요청입니다."),

	DUPLICAT_USER(HttpStatus.BAD_REQUEST,"E6", "이미 생성된 계정이 있습니다."),
	LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "E7", "계정 혹은 비밀번호가 틀립니다."),
	VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "E8", "유효성 검증이 실패 했습니다."),
	
	ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "A1", "존재하지 않는 아티클입니다.");

	private final String message;
	private final String code;
	private final HttpStatus status;

	ErrorCode(final HttpStatus status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
