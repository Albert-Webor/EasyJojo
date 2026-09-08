package com.easyjojo.common.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.easyjojo.common.utils.JojoPrint.print_green;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        print_green("执行了一次preHandle 方法"+ LocalDateTime.now() +LocalTime.now());
        return true; // 返回true表示继续处理请求，返回false表示中断请求
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                   @Nullable Exception ex){
        print_green("执行了一次afterCompletion 方法"+ LocalDateTime.now() +LocalTime.now());
    }
}
