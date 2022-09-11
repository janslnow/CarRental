package com.demo.rental.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HttpResult<T> {

    public static final int success = 200;
    public static final int fail = 110;

    @ApiModelProperty("status code. 200:success, 110:fail")
    private Integer code;
    private String message;
    private Long serverTime;

    @ApiModelProperty("data")
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
