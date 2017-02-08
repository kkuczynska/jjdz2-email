package com.jbd.authorization;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"App/AdminConsole.jsp"})
public class PrivilegeFilter implements Filter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PrivilegeFilter.class);
    private static final Marker MARKER = MarkerFactory.getMarker("PrivilegeFilter");

    @Inject
    SessionData sessionData;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (sessionData.getPrivilege() == SessionData.LOCAL_USER) {
            LOGGER.info(MARKER, "User is not previliged to access this page - user previlige:  " + sessionData.getPrivilege() + ", require Admin");
            ((HttpServletResponse) servletResponse).sendRedirect("/jbdee/index.jsp");
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
