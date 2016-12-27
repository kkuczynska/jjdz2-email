package com.jbd.Authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/App/*"})
public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);

    @Inject SessionData sessionData;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!sessionData.isLogged()){
            LOGGER.info("User is not Logged to access this page");
            ((HttpServletResponse) servletResponse).sendRedirect("/jbdee/LoginFB.jsp");
            return;
        }

        servletRequest.setAttribute("sessionData", sessionData);
        filterChain.doFilter(servletRequest,servletResponse);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
