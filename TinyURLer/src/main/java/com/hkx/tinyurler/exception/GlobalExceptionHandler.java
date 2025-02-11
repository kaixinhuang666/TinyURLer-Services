package com.hkx.tinyurler.exception;

//import com.hkx.tinyurler.dto.response.UrlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理 UrlNotFoundException 异常，返回 404 状态码
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleUrlNotFoundException(UrlNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 返回 404 错误和错误信息
    }

    // 处理 IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + ex.getMessage()); // 返回 400 错误和错误信息
    }

    // 处理 UserAlreadyExistsException
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // 处理 InvalidCredentialsException
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // 限流异常
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<String> handleRateLimitExceededException(RateLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage());
    }


    // 捕获其他异常，返回 500 状态码
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
