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

return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /* IllegalArgumentException 처 */

        // 403 권한없음 예외처리
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus>BAD_REQUEST.value());
            body.put("message", ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    /*
     * RuntimeException 처리
     */

     @ExceptionHandler(RuntimeException.class)
     public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("messae", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

     }

     /*
      * 기타 그 외 모든 예외처리
      */

      @ExceptionHamdler(Exception.class)
      public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        
      }

    }
