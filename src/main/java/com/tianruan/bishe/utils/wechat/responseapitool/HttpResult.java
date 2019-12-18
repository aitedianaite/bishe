package com.tianruan.bishe.utils.wechat.responseapitool;

import org.springframework.http.HttpStatus;


public class HttpResult {
    private boolean success;
    private String result;
    private HttpStatus httpStatus;
    private long ts;

    public HttpResult(boolean success, String result, HttpStatus httpStatus) {
        this.success = success;
        this.result = result;
        this.httpStatus = httpStatus;
    }

    public HttpResult(String result) {
        this.success = true;
        this.result = result;
        this.httpStatus = HttpStatus.OK;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public static HttpResult success(String result) {
        return new HttpResult(result);
    }

    public static HttpResult fail(String result, HttpStatus httpStatus) {
        return new HttpResult(false, result, httpStatus);
    }
}
