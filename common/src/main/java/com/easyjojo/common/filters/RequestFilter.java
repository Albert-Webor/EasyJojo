package com.easyjojo.common.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.easyjojo.common.utils.PrintUtils.printCyan;
import static com.easyjojo.common.utils.PrintUtils.printYellow;

public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化（容器启动时只执行一次）
        printYellow("[Filter] TraceFilter 初始化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 强转为 HTTP 相关的 Request / Response
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();
        String uri = req.getRequestURI();
        printCyan(">>> [Filter 进] 接收到请求: " + req.getMethod() + " " + uri);

        try {
            // ⭐ 核心放行方法：将请求传递给下一个 Filter，直到进入 Spring 的 DispatcherServlet 和 Controller
            chain.doFilter(request, response);
        } finally {
            // 请求处理完毕，返回给客户端时触发
            long cost = System.currentTimeMillis() - startTime;
            printCyan("<<< [Filter 出] 请求完成: " + uri + "，状态码: " + resp.getStatus() + "，耗时: " + cost + "ms");
        }
    }

    @Override
    public void destroy() {
        // 过滤器销毁（容器关闭时执行）
        printYellow("[Filter] TraceFilter 销毁成功");
    }
}
