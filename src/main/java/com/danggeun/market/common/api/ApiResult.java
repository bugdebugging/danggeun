package com.danggeun.market.common.api;

public class ApiResult<T> {
    private T data;
    private ApiError error;

    public ApiResult(T data, ApiError error) {
        this.data = data;
        this.error = error;
    }

    public static class ApiError {
        public int status;
        public String message;

        public ApiError(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
