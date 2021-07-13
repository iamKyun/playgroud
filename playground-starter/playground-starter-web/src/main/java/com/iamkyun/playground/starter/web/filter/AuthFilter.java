package com.iamkyun.playground.starter.web.filter;

import com.iamkyun.playground.tools.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Long token = Long.parseLong(req.getHeader("x-user-token"));
        Authentication.setCurrentUserId(token);
        chain.doFilter(request, response);
    }
}
