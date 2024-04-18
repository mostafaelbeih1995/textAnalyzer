package com.Dedalus.TextAnalyzer.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {
    @Builder.Default
    private Boolean success = true;
    private ErrorPayload errors;
    @Builder.Default
    private Integer code = HttpStatus.OK.value();
    private T payload;
    private String serviceTime;

    public static <T> ErrorResponse<T> ok(T payload) {
        return status(HttpStatus.OK, payload, null);
    }

    public static <T> ErrorResponse<T> created(T payload) {
        return status(HttpStatus.CREATED, payload, null);
    }

    public static <T> ErrorResponse<T> accepted(T payload) {
        return status(HttpStatus.ACCEPTED, payload, null);
    }

    public static <T> ErrorResponse<T> ok(T payload, String serviceTime) {
        return status(HttpStatus.OK, payload, serviceTime);
    }

    public static <T> ErrorResponse<T> created(T payload, String serviceTime) {
        return status(HttpStatus.CREATED, payload, serviceTime);
    }
    public static <T> ErrorResponse<T> noContent(String serviceTime) {
        return status(HttpStatus.NO_CONTENT, null, serviceTime);
    }

    public static <T> ErrorResponse<T> accepted(T payload, String serviceTime) {
        return status(HttpStatus.ACCEPTED, payload, serviceTime);
    }
    public static <T> ErrorResponse<T> failed() {
        return status(HttpStatus.EXPECTATION_FAILED,  null,null);
    }
    private static <T> ErrorResponse<T> status(HttpStatus status, T payload, String serviceTime) {
        return new ErrorResponse<>(true, null, status.value(), payload, serviceTime);
    }

}
