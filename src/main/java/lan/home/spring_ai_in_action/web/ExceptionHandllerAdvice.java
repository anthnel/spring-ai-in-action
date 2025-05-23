package lan.home.spring_ai_in_action.web;

import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class) // #2
    public ProblemDetail handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        List<String> validationMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
        problemDetail.setProperty("validationErrors", validationMessages); // #3
        return problemDetail;
    }

}
