package com.hoon.article.exception;

public class AuthFailException extends BusinessBaseException {

	public AuthFailException() {
		super(ErrorCode.AUTH_FAIL);
	}
	
}
