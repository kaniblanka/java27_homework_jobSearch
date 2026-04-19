package kg.attractor.jobsearch.exception.handler;

import kg.attractor.jobsearch.exception.BaseException;
import kg.attractor.jobsearch.exception.NotFoundEntryException;
import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice(basePackages = "kg.attractor.jobsearch.controller.api")
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    public ResponseEntity<ErrorResponseBody> handleNotFound(NotFoundEntryException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponseBody> handleBaseException(BaseException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> handleValidation(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e.getBindingResult()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBody> handleOther(Exception e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}