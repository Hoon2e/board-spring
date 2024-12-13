package com.hoon.article.exception;

public class DuplicatUserException extends BusinessBaseException {

	
	public DuplicatUserException(ErrorCode errorCode) {
		super(errorCode.getMessage(), errorCode);
	}

	public DuplicatUserException() {
		super(ErrorCode.DUPLICAT_USER);
	}

}
