package com.hoon.article.exception;

public class LoginFailException extends BusinessBaseException {

	public LoginFailException() {
		super(ErrorCode.LOGIN_FAIL);
	}
}
