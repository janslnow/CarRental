package com.demo.rental.filter;

import com.demo.rental.common.UserContext;
import com.demo.rental.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@Order(1)
@WebFilter(urlPatterns = {"/api/*"}, filterName = "sessionValidateFilter")
public class SessionValidateFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //this web demo don't implement login function;
        //so all request use a test user info for test;
        //generally we need valid the session of request in this position then ;
        User user = new User();
        user.setUserId(1);
        user.setUserName("Demo Test User");

        UserContext.serUser(user);

        filterChain.doFilter(servletRequest, servletResponse);

        UserContext.clear();
    }
}
