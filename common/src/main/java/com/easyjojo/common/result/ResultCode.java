package com.easyjojo.common.result;

/**
 * 常用响应状态码枚举
 */
public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权或登录已过期"),
    FORBIDDEN(403, "没有访问权限"),
    NOT_FOUND(404, "请求资源未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    INTERNAL_SERVER_ERROR(500, "系统内部异常");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
