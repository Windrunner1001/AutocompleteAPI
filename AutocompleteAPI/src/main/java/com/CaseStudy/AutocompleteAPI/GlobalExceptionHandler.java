package com.CaseStudy.AutocompleteAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class-collection provides all custom error messages and a class ErrorResponse for the structure of the custom JSON Error response.
 **/


class ErrorResponse {
    private String error_code;
    private String error_description;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}

class CSVFileEmpty extends RuntimeException {
    CSVFileEmpty() {
        super("There are no values in the CSV-file.");
    }
}

class UserInputTooShort extends RuntimeException {
    UserInputTooShort() {
        super("Please enter at least three digits in the input.");
    }
}

class NoUserInput extends RuntimeException {
    NoUserInput() {
        super("Please enter at least three digits in the input.");
    }
}

class UserInputNotAlphabetic extends RuntimeException {
    UserInputNotAlphabetic() {
        super("The input only accepts characters. Please do not enter any numeric or alphanumeric values.");
    }
}

class NoMatches extends RuntimeException {
    NoMatches() {
        super("There are no matches regarding your input.");
    }
}

class HeaderNotFound extends RuntimeException {
    HeaderNotFound() {
        super("There is no match in the header row for the keyword 'NAME'. Please check the structure and content of the CSV-file. ");
    }
}

class IncompleteUrlException extends RuntimeException {
    IncompleteUrlException() {
        super("The URL is incomplete. Please use the schema '/api/v1/auto-complete/{userInput}'.");
    }
}

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoMatches.class)
    public ResponseEntity<ErrorResponse> NoMatches(NoMatches ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CSVFileEmpty.class)
    public ResponseEntity<ErrorResponse> CSVFileEmpty(CSVFileEmpty ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserInputNotAlphabetic.class)
    public ResponseEntity<ErrorResponse> UserInputNotAlphabetic(UserInputNotAlphabetic ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(NoUserInput.class)
    public ResponseEntity<ErrorResponse> NoUserInput(NoUserInput ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(UserInputTooShort.class)
    public ResponseEntity<ErrorResponse> UserInputTooShort(UserInputTooShort ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(HeaderNotFound.class)
    public ResponseEntity<ErrorResponse> HeaderNotFound(HeaderNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IncompleteUrlException.class)
    public ResponseEntity<ErrorResponse> IncompleteUrlException(IncompleteUrlException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.setError_description("An unexpected error occurred. Further information: " + ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
