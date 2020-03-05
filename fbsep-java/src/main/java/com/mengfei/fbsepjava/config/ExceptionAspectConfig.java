package com.mengfei.fbsepjava.config;

import com.mengfei.fbsepjava.exception.TokenException;
import com.mengfei.fbsepjava.pojo.ApiResponse;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理切面
 * Description: 利用 @ControllerAdvice + @ExceptionHandler 组合处理Controller层RuntimeException异常
 * 在运行时从上往下依次调用每个异常处理方法，匹配当前异常类型是否与@ExceptionHandler注解所定义的异常相匹配，
 * 若匹配，则执行该方法，同时忽略后续所有的异常处理方法，最终会返回经JSON序列化后的Response对象
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAspectConfig {

    /** Log4j日志处理 */
    private static final Logger log = Logger.getLogger(ExceptionAspectConfig.class);

    /**
     * 1000 Token验证未通过
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public ApiResponse handleTokenException(TokenException e) {
        log.error("Token验证未通过", e);
        return new ApiResponse<>(1000,"Token验证未通过",null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("could_not_read_json...", e);
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"could_not_read_json...",null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleValidationException(MethodArgumentNotValidException e) {
        // 获取BindingResult对象，然后获取其中的错误信息
        // 如果开启了fail_fast，这里只会有一个信息
        // 如果没有，则可能会有多个验证提示信息
        List<String> errorInformation = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(errorInformation.toString(), e);
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),errorInformation.toString(),null);
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("request_method_not_supported...", e);
        return new ApiResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(),"request_method_not_supported",null);
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content_type_not_supported...", e);
        return new ApiResponse<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),"content_type_not_supported...",null);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        log.error("Internal Server Error...", e);
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error...",null);
    }
}