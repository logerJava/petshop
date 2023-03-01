package com.loger.petshop.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author loger
 * @date 2023/2/27 11:10
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int status;
    private String message;
    private T data;

    public Result(HttpStatus status) {
        this(status, null);
    }

    public Result(HttpStatus status, T data) {
        this(status, status.getReasonPhrase(), data);
    }

    public Result(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    /**
     * @return 默认成功响应
     */
    public static Result<Void> success() {
        return new Result<>(HttpStatus.OK);
    }

    /**
     * 自定义信息的成功响应
     * <p>通常用作插入成功等并显示具体操作通知如: return Result.success("发送信息成功")</p>
     * @param message 信息
     * @return 自定义信息的成功响应
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(HttpStatus.OK, message, null);
    }

    /**
     * 带数据的成功响应
     * @param data 数据
     * @return 带数据的成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatus.OK, data);
    }

    /**
     * @return 默认失败响应
     */
    public static <T> Result<T> fail() {
        return new Result<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                null
        );
    }

    /**
     * 自定义错误信息的失败响应
     * @param message 错误信息
     * @return 自定义错误信息的失败响应
     */
    public static <T> Result<T> fail(String message) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * 自定义状态的失败响应
     * @param status 状态
     * @return 自定义状态的失败响应
     */
    public static <T> Result<T> fail(HttpStatus status) {
        return fail(status, status.getReasonPhrase());
    }

    /**
     * 自定义状态和信息的失败响应
     * @param status  状态
     * @param message 信息
     * @return 自定义状态和信息的失败响应
     */
    public static <T> Result<T> fail(HttpStatus status, String message) {
        return new Result<>(status, message, null);
    }

}
