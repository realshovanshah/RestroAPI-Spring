package com.realshovanshah.restroapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Optional;

@Component
public class TestFilter implements Filter {

    @Autowired
    RedisIndexedSessionRepository redisSessionRepository;

    @Autowired
    FindByIndexNameSessionRepository sessionRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("before");
        filterChain.doFilter(servletRequest, servletResponse);
            Optional<String> haha = redisSessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, SecurityContextHolder.getContext().getAuthentication().getName()).keySet().stream().findAny();
        final ServletResponseWrapper responseWrapper = (ServletResponseWrapper) servletResponse;
        responseWrapper.ge
//        responseWrapper.getResponse().resetBuffer();
//        final byte[] copy = responseWrapper.getResponse();
//        final String oldBody = new String(copy, response.getCharacterEncoding());
        final String newBody ="\"name\" : \""+haha.get()+"\"";
            responseWrapper.getResponse().getOutputStream().write(newBody.getBytes());
        System.out.println("after");



    }

    @Override
    public void destroy() {

    }
}
