package kg.attractor.jobsearch.exception.handler;

import kg.attractor.jobsearch.exception.BaseException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.NotFoundEntryException;
import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    private ResponseEntity<ErrorResponseBody> notFoundExceptionHandler(NotFoundEntryException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BaseException.class)
    private ResponseEntity<ErrorResponseBody> baseExceptionHandler(BaseException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseBody> validationExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e.getBindingResult()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseBody> commonExceptionHandler(Exception e) {
        return new ResponseEntity<>(
                errorService.makeResponse(e, e.getClass().getSimpleName()),
                HttpStatus.BAD_REQUEST
        );
    }
}