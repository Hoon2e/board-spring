package com.hoon.article.dto.common;

import org.springframework.http.HttpStatus;

import com.hoon.article.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {

    // 상수 정의
    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String FAILURE_STATUS = "FAILURE";

    private static final int DEFAULT_SUCCESS_CODE = HttpStatus.OK.value(); // 200
    private static final int DEFAULT_FAILURE_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value(); // 500

    private int statusCode;  // HTTP 상태 코드
    private String status;   // SUCCESS 또는 FAILURE
    private T data;          // 실제 응답 데이터 (제네릭 타입)
    private String message;  // 응답 메시지
    private String errorCode;

    // 성공 응답
    public static <T> ResponseDto<T> success(T data, int statusCode) {
        return ResponseDto.<T>builder()
            .statusCode(statusCode)
            .status(SUCCESS_STATUS)
            .data(data)
            .build();
    }

    public static <T> ResponseDto<T> success(T data, HttpStatus statusCode) {
        return success(data, statusCode != null ? statusCode.value() : DEFAULT_SUCCESS_CODE);
    }

    public static <T> ResponseDto<T> success(T data) {
        return success(data, DEFAULT_SUCCESS_CODE);
    }

    // 실패 응답
    public static <T> ResponseDto<T> failure(ErrorCode errorCode) {
        return ResponseDto.<T>builder()
            .statusCode(errorCode.getStatus().value())
            .status(FAILURE_STATUS)
            .data(null)
            .message(errorCode.getMessage())
            .errorCode(errorCode.getCode())
            .build();
    }
}

