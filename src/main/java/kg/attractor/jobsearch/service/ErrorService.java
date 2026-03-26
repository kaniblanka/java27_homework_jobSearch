package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.exception.handler.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception e, String exceptionClass);
    ErrorResponseBody makeResponse(BindingResult bindingResult);
}