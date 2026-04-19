package kg.attractor.jobsearch.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.exception.BaseException;
import kg.attractor.jobsearch.exception.NotFoundEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalMvcExceptionHandler {

    @ExceptionHandler(NotFoundEntryException.class)
    public String handleNotFound(
            NotFoundEntryException e,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", "Not Found");
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path", request.getRequestURI());

        return "errors/error";
    }

    @ExceptionHandler(BaseException.class)
    public String handleBaseException(
            BaseException e,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("error", "Bad Request");
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path", request.getRequestURI());

        return "errors/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(
            Exception e,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "Internal Server Error");
        model.addAttribute("message", "Something went wrong");
        model.addAttribute("path", request.getRequestURI());

        return "errors/error";
    }
}