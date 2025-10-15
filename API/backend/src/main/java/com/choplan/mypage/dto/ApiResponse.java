package com.choplan.mypage.dto;

public record ApiResponse<T> (boolean ok, T data, String message) {
    public static <T> ApiResponse<T> ok(T data) { return new ApiResponse<>(true, data, null); }
    public static <T> ApiResponse<T> fail(String msg) { return new ApiResponse<T>(false, null, msg); }
}
