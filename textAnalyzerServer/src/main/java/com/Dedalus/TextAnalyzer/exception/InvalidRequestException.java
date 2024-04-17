package com.Dedalus.TextAnalyzer.exception;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends RuntimeException {

    private BindingResult bindingResult;

    public InvalidRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    @Override
    public String getMessage() {
        // Generate a meaningful error message based on the validation errors
        // You can iterate over the bindingResult to extract detailed error information
        // ...

        return "Invalid request. Please provide valid inputs.";
    }
}