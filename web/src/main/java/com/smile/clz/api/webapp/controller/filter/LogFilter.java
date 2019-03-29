package com.smile.clz.api.webapp.controller.filter;

import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.smile.clz.api.core.Constants.CORRELATION_ID_HEADER;
import static com.smile.clz.api.core.Constants.CORRELATION_ID_INTERNAL_KEY;

/**
 * Filter class to add incoming correlation id to the required contexts.
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //This method will not be implemented
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (request instanceof  HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            final String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER);
            if (correlationId != null) {
                MDC.put(CORRELATION_ID_INTERNAL_KEY, correlationId);
            }
        } else {
            MDC.put(CORRELATION_ID_INTERNAL_KEY, null);
        }
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //This method will not be implemented
    }

}
