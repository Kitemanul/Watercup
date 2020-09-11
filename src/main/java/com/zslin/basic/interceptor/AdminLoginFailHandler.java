package com.zslin.basic.interceptor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AdminLoginFailHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {


        String errMsg = null;
        if (e instanceof UsernameNotFoundException)
        {
            errMsg = "该用户不存在";
        } else if(e instanceof LockedException){
            errMsg = "该用户被冻结";
        } else if(e instanceof BadCredentialsException){
            errMsg = "密码错误";
        }


        httpServletRequest.getSession().setAttribute("errMsg",errMsg);

        httpServletRequest.getRequestDispatcher("/loginfail").forward(httpServletRequest,httpServletResponse);




    }
}
