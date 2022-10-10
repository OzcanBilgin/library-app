package com.library.app.libraryapp.dto;

import com.library.app.libraryapp.enums.StatusEnum;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {
    private static final RestResponse<Void> EMPTY_RESPONSE = new RestResponse<>(null, HttpStatus.OK.value(), StatusEnum.SUCCESS,null);

    private static final RestResponse<Void> CREATED_RESPONSE = new RestResponse<>(null, HttpStatus.CREATED.value(), StatusEnum.SUCCESS,null);

    private T data;

    private int status;

    private StatusEnum message;

    private String errorMessage;


    private RestResponse(T t, int status, StatusEnum message,String errorMessage){
        this.data = t;
        this.message = message;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static <T> RestResponse<T> of(T t, int status, StatusEnum message,String errorMessage){
        return new RestResponse<>(t, status, message,errorMessage);
    }

    public static RestResponse<Void> empty() {
        return EMPTY_RESPONSE;
    }

    public static RestResponse<Void> created() {
        return CREATED_RESPONSE;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public StatusEnum getMessage() {
        return message;
    }

    public void setMessage(StatusEnum message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
