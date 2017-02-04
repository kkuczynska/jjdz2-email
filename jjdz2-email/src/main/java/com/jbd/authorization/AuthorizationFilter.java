package com.jbd.authorization;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/App/*"})
public class AuthorizationFilter implements Filter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private static final Marker MARKER = MarkerFactory.getMarker("AuthorizationFilter");

    @Inject
    SessionData sessionData;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!sessionData.isLogged()) {
            LOGGER.info(MARKER, "User is not Logged to access this page");
            ((HttpServletResponse) servletResponse).sendRedirect("/jbdee/LoginFB.jsp");
            return;
        }

        servletRequest.setAttribute("sessionData", sessionData);
        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
