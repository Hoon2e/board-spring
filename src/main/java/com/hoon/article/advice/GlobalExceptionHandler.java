package com.hoon.article.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoon.article.dto.common.ResponseDto;
import com.hoon.article.exception.BusinessBaseException;
import com.hoon.article.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessBaseException.class)
    protected ResponseEntity<ResponseDto<Void>> handle(BusinessBaseException e) {
        log.error("BusinessException", e);
        return createErrorResponseEntity(e.getErrorCode());
    }
    
    
    // 유효성 검증 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ResponseDto<Void> responseDto = ResponseDto.failure(ErrorCode.VALIDATION_FAIL);
        responseDto.setMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("BAD_REQUEST", e);
        return createErrorResponseEntity(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleGeneralException(Exception e) {
    	log.error("INTERNAL_SERVER_ERROR", e);
    	return createErrorResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

	private ResponseEntity<ResponseDto<Void>> createErrorResponseEntity(ErrorCode errorCode) {
		ResponseDto<Void> res = ResponseDto.failure(errorCode);
        return ResponseEntity.status(res.getStatusCode()).body(res);
	}
}
