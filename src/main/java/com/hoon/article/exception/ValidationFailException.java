package com.hoon.article.exception;

public class ValidationFailException extends BusinessBaseException {

	public ValidationFailException() {
		super(ErrorCode.VALIDATION_FAIL);
	}

}
