package com.makrosoft.pruebab.utils.exception.handler;

import com.makrosoft.pruebab.utils.exception.BusinessRuleException;
import com.makrosoft.pruebab.utils.response.Response;
import com.makrosoft.pruebab.utils.response.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
@RestControllerAdvice
public class CustomExceptionHandler{
    private static final ResourceBundle bundle =
            ResourceBundle.getBundle("exceptions", LocaleContextHolder.getLocale());
    private static String ERROR = bundle.getString("bad.generic.request");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return new ResponseEntity<>(new ResponseHandler<>(400, ERROR, ERROR, errors).getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<Map<String, String>>> handleGenericException(final HttpServletRequest req,
                                                                                final BusinessRuleException ex, final Locale locale) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(new ResponseHandler<>(400, ERROR, ERROR, error).getResponse(), HttpStatus.BAD_REQUEST);
    }
}
