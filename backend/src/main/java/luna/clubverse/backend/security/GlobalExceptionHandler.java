package luna.clubverse.backend.security;

import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public MessageResponse handleException(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors()
                .stream()
                .map(error -> new MessageResponse(MessageType.ERROR, error.getDefaultMessage()))
                .toList()
                .get(0);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public MessageResponse handleException2(EntityNotFoundException exception) {
        return new MessageResponse(MessageType.ERROR,exception.getMessage());
    }


}
