package lv.javaguru.java2.web;

import lv.javaguru.java2.businesslogic.ApplicationException;
import lv.javaguru.java2.web.dtos.ApplicationExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> handleApplicationException(ApplicationException ex,
                                                                WebRequest request) {
        ApplicationExceptionDTO appExDTO = new ApplicationExceptionDTO(ex.getErrors());
        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(ApplicationException ex,
                                                                WebRequest request) {
        return handleExceptionInternal(ex, ex,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}
