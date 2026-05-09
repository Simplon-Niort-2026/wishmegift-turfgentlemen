package co.simplon.wishmegift.exception;

import co.simplon.wishmegift.entity.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorEntity> userNotFoundHandler(ResourceNotFoundException exception) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(exception.getMessage());
        errorEntity.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<ErrorEntity> emailAlreadyExistHandler(EmailAlreadyExist exception) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(exception.getMessage());
        errorEntity.setHttpStatusCode(HttpStatus.CONFLICT.value());
        errorEntity.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorEntity, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorEntity> runTimeExceptionHandler(RuntimeException exception) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(exception.getMessage());
        errorEntity.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorEntity.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorEntity, HttpStatus.UNAUTHORIZED);

    }
}
