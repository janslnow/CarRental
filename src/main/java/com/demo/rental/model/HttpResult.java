package com.demo.rental.model;

import lombok.Data;

@Data
public class HttpResult<T> {

    public static final int success = 200;
    public static final int fail = 110;

    private Integer code;
    private String message;
    private Long serverTime;
    private T data;

    private HttpResult() {

    }


    public static HttpResult<?> success() {
        HttpResult<?> httpResult = new HttpResult<>();
        httpResult.setCode(success);
        httpResult.setMessage("success");

        return httpResult;
    }

    public static HttpResult<?> fail() {

        HttpResult<?> httpResult = new HttpResult<>();
        httpResult.setCode(fail);
        httpResult.setMessage("fail");

        return httpResult;
    }

    public static <X> HttpResult<X> success(X data) {

        HttpResult<X> httpResult = new HttpResult<>();
        httpResult.setCode(success);
        httpResult.setMessage("success");
        httpResult.setData(data);

        return httpResult;
    }

    public static HttpResult<?> fail(String message) {

        HttpResult<?> httpResult = new HttpResult<>();
        httpResult.setCode(fail);
        httpResult.setMessage(message);

        return httpResult;
    }

}
