package com.easyjojo.common.result;

import java.io.Serializable;

/**
 * 统一通用 API 泛型响应结果类
 *
 * @param <T> 数据载体类型
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务状态码 (例如: 200 成功, 500 异常)
     */
    private int code;

    /**
     * 响应消息提示
     */
    private String message;

    /**
     * 响应核心数据
     */
    private T data;

    /**
     * 响应时间戳
     */
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    // ======================== 快速构建成功结果 ========================

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // ======================== 快速构建失败结果 ========================

    public static <T> Result<T> error() {
        return error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    public static <T> Result<T> error(IResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> error(IResultCode resultCode, String message) {
        return new Result<>(resultCode.getCode(), message, null);
    }

    // ======================== Getter & Setter ========================

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
