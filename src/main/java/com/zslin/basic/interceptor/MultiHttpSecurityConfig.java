package com.zslin.basic.interceptor;

import com.zslin.web.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;


@EnableWebSecurity
@Configuration
public class MultiHttpSecurityConfig{

    @Configuration
    @Order(2)
    public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        AdminLoginSuccessHandler adminLoginSuccessHandler;

        @Autowired
        AdminLoginFailHandler loginFailHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .formLogin()
            .loginPage("/admin/basic/login")
            .loginProcessingUrl("/adminlogin.do")
            .successHandler(adminLoginSuccessHandler)
            .failureHandler(loginFailHandler)

            ;

        }

        @Autowired
        AdminLoginDetailService adminLoginDetailService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(adminLoginDetailService).passwordEncoder(new BCryptPasswordEncoder());

        }
    }

    @Configuration
    @Order(1)
    public class AccountSecurityConfig extends WebSecurityConfigurerAdapter{


        @Autowired
        WebLoginSuccessHandler webLoginSuccessHandler;
        @Autowired
        WebLoginFailHandler webLoginFailHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .antMatcher("/web/**")
            .formLogin()
            .loginPage("/web/login")
            .loginProcessingUrl("/web/login.do")
            .usernameParameter("email")
            .successHandler(webLoginSuccessHandler)
            .failureUrl("/web/login")


            ;
        }

        @Autowired
        WebLoginDetailService webLoginDetailService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(webLoginDetailService).passwordEncoder(new BCryptPasswordEncoder());
        }

    }




}

