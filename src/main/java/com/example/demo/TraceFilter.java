package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.example.demo.Constant.MDC_TRACE;

@Component
@Slf4j
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // "traceId"
        MDC.put(MDC_TRACE, getTraceId());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        return timestamp + uuid.toString().replace("-", "");
    }
}

