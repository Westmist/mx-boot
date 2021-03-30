package com.lcc.api.vo;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 返回成功标识
     */
    private Boolean success;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回提示消息
     */
    private String message;

    /**
     * 返回的数据对象
     */
    private Object data;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 不带数据对象的VO
     *
     * @param message 返回的消息提示
     * @return
     */
    public static Result<Object> ok(String message) {
        Result<Object> r = new Result<>();
        r.setCode(200);
        r.setSuccess(true);
        r.setMessage(message);
        return r;
    }

    /**
     * 带数据对象的VO
     *
     * @param message 返回的消息提示
     * @param data    需要返回的数据
     * @return
     */
    public static Result<Object> ok(String message, Object data) {
        Result<Object> r = new Result<>();
        r.setCode(200);
        r.setSuccess(true);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /**
     * 返回带错误信息的VO
     * @param code  状态码
     * @param message 返回的消息提示
     * @return
     */
    public static Result<Object> error(Integer code, String message) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setSuccess(false);
        r.setMessage(message);
        return r;
    }
}
