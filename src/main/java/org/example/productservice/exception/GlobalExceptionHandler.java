package org.example.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================================
    // 400 - VALIDATION ERROR
    // =========================================

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiResponseError>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()

                        .map(error ->
                                error.getField()
                                        + ": "
                                        + error.getDefaultMessage()
                        )

                        .collect(
                                Collectors.joining("; ")
                        );


        ApiResponseError response =
                ApiResponseError
                        .builder()

                        .timestamp(
                                LocalDateTime.now()
                        )

                        .status(
                                HttpStatus.BAD_REQUEST.value()
                        )

                        .error(
                                "Bad Request"
                        )

                        .message(
                                message
                        )

                        .build();


        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(response);
    }

    // =========================================
    // 404 - PRODUCT NOT FOUND
    // =========================================

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ApiResponseError>
    handleNotFound(
            ResourceNotFoundException ex
    ) {

        ApiResponseError response =
                ApiResponseError
                        .builder()

                        .timestamp(
                                LocalDateTime.now()
                        )

                        .status(
                                HttpStatus.NOT_FOUND.value()
                        )

                        .error(
                                "Not Found"
                        )

                        .message(
                                ex.getMessage()
                        )

                        .build();

        return ResponseEntity
                .status(
                        HttpStatus.NOT_FOUND
                )
                .body(response);
    }
}
