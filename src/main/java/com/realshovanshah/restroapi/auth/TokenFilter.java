package com.realshovanshah.restroapi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.realshovanshah.restroapi.auth.dto.AuthenticationResponse;
import javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("before");

        HttpServletResponseWrapper responseWrapper = (HttpServletResponseWrapper) servletResponse;
        responseWrapper.getResponse().setContentType("application/json");


        filterChain.doFilter(servletRequest, responseWrapper);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        System.out.println(attr.getSessionId());

        ObjectMapper ow = new ObjectMapper();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(attr.getSessionId(), username);
        String json = ow.writeValueAsString(authenticationResponse);
        responseWrapper.getResponse().getOutputStream().write(json.getBytes());


//        Optional<String> haha = redisSessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, SecurityContextHolder.getContext().getAuthentication().getName()).keySet().stream().findAny();
//        System.out.println(haha);



//        final byte[] copy = responseWrapper.getContentAsByteArray();
//        String responseStr=new String(copy,responseWrapper.getCharacterEncoding());
//        AuthenticationResponse authenticationRespons1 = ow.readValue(responseStr, AuthenticationResponse.class);
//        authenticationRespons1.setAuthToken(haha.get());
//        authenticationRespons1.setEmail(username);
//        System.out.println(authenticationRespons1);
//        responseWrapper.getResponse().getOutputStream().write(json.getBytes());
//        responseWrapper.copyBodyToResponse();



        System.out.println("after");
//        HttpServletResponse httpRequest = (HttpServletResponse) responseWrapper;
//        Map var = httpRequest.getHeaderNames().stream().collect(Collectors.toMap(Function.identity(),h -> new ArrayList<>(httpRequest.getHeaders(h)),(oldValue, newValue) -> newValue,HttpHeaders::new));
//        Optional<String> test = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, SecurityContextHolder.getContext().getAuthentication().getName()).keySet().stream().findAny();
//        System.out.println(test);




    }

    @Override
    public void destroy() {

    }

//    System.out.println("before");
//    String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(redisSessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username).keySet().stream().findFirst());
//    Optional<String> haha = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username).keySet().stream().findFirst();
//        servletResponse.resetBuffer();
//        filterChain.doFilter(servletRequest, servletResponse);
//    final HttpServletResponseWrapper responseWrapper = (HttpServletResponseWrapper) servletResponse;
//        responseWrapper.setContentType("application/json");
//    AuthenticationResponse authenticationResponse = new AuthenticationResponse(haha.get(), username);
//    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//    String json = ow.writeValueAsString(authenticationResponse);
//            responseWrapper.getResponse().getOutputStream().write(json.getBytes());
//        System.out.println("after");
}


