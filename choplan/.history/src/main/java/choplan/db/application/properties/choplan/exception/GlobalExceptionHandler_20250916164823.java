package choplan.db.application.properties.choplan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * 프로젝트 전역에서 발생한 예외 처리 클래스 모든 컨트롤러에서 발생하는 예외를 잡아 표준 응답 형태로 변환
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*유효성 검사 실패 (@Valid) 예외처리 */
    // DTO 유효성 검사
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());

        }

Map<String, Object> body = new HashMap<>();
body.put("status", HttpStatus.BAD_REQUEST.value());
body.put("message", "요청값이 올바르지 않습니다.");
body.put("errors", errors);


        // 403 권한없음 예외처리
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDenied
        (AccessDeniedException ex
            
        ) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new AuthResponse(403, "접근 권한이 없습니다.", null));
        }

    }
