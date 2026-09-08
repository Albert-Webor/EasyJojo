package com.easyjojo.common.result;

/**
 * 响应状态码规范接口
 */
public interface IResultCode {

    /**
     * 获取状态码
     */
    int getCode();

    /**
     * 获取状态描述信息
     */
    String getMessage();
}
