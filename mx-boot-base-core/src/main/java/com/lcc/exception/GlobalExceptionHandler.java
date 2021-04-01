package com.lcc.exception;

import com.lcc.api.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author Cong-Cong Liao
 * @since 2021-04-01
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕捉Contoller的请求参数校验异常
     * 默认校验所有属性
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handle(MethodArgumentNotValidException exception) {
        String validatedErrorMesssage = "";
        if (exception instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = exception.getBindingResult();
            List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();
            for (int i = 0; i < fieldErrorsList.size(); i++) {
                // 拼接校验错误消息
                validatedErrorMesssage += fieldErrorsList.get(i).getDefaultMessage();
                if (i != fieldErrorsList.size() - 1) {
                    validatedErrorMesssage += ",";
                }
            }
        }
        return Result.error(201, validatedErrorMesssage);
    }
}
