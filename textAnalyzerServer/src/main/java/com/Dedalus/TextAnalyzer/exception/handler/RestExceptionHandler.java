package com.Dedalus.TextAnalyzer.exception.handler;

import com.Dedalus.TextAnalyzer.exception.ExceptionList;
import com.Dedalus.TextAnalyzer.exception.InvalidRequestException;
import com.Dedalus.TextAnalyzer.model.payload.ErrorPayload;
import com.Dedalus.TextAnalyzer.model.payload.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex) {
        Map<String, Object> errors = new HashMap<>();
        String message = prepareMessages(ExceptionList.INVALID_ARGUMENT);
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> errors = new HashMap<>();
        String message = prepareMessages(ExceptionList.WRONG_TYPE_PROVIDED);
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleErrorResponse(String enMessage, String type, Map<String, Object> errors, HttpStatus status) {

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .enMessage(enMessage)
                                .type(type).details(errors).detailsAsString(errors == null ? "" : errors.toString()).build())
                        .code(status.value())
                        .build(), status);
    }

    private String prepareMessages(String error) {

        try {
            return getMessage(error, "en");
        } catch (NoSuchMessageException messageEx) {
            String en;
            en = getMessage(ExceptionList.INVALID_ARGUMENT, "en");
            return en;
        }
    }

    private String getMessage(String error, String language) {

        if ("en".equalsIgnoreCase(language))
            return messageSource.getMessage(error, null, new Locale("en"));
        else
            return messageSource.getMessage(error, null, new Locale("ar"));
    }
}
