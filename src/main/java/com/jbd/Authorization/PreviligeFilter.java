package com.jbd.Authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;

        import javax.inject.Inject;
        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;


@WebFilter(urlPatterns = {"/App/AdminConsole.jsp"})
public class PreviligeFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(PreviligeFilter.class);

    @Inject
    SessionData sessionData;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(sessionData.getPrivilege().equals("local")){
            LOGGER.info("User is not previliged to access this page - user previlige:  " + sessionData.getPrivilege() +", require Admin");
            ((HttpServletResponse) servletResponse).sendRedirect("/jbdee/App/form.jsp");
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
