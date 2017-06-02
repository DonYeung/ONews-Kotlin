package com.don.onews_kotlin.api;


import com.don.onews_kotlin.bean.HttpResult;

/**
 * Created by drcom on 2017/3/16.
 */

public class ApiException extends RuntimeException {



    public ApiException(HttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(HttpResult httpResult){
        String message = "";
        switch (httpResult.getError_code()) {
            case 10001:
                message = "ERROR:错误的请求KEY";
                break;
            case 10021:
                message = "ERROR:接口停用";
                break;
            case 10013:
                message = "ERROR:测试KEY超过请求限制";
                break;
            default:
                message = "ERROR:网络连接异常";

        }
        return message;
    }
}


