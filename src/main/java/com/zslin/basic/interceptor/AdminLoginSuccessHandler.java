package com.zslin.basic.interceptor;

import com.zslin.basic.dto.AuthToken;
import com.zslin.basic.model.User;
import com.zslin.basic.service.IMenuService;
import com.zslin.basic.service.IUserService;
import com.zslin.basic.service.MenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private MenuServiceImpl menuServiceImpl;
    @Autowired
    private IUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("认证成功");



        User u=(User) authentication.getPrincipal();


        AuthToken at = new AuthToken();
        at.setLogin_ip(request.getRemoteAddr());
        at.setLogin_time(new Date());
        at.setUser(u);
        at.setAuthMenu(menuServiceImpl.queryMenuDtoNew(u.getId()));
        List<String> authList = menuService.listAuthByUser(u.getId());
        authList.add("AdminController.index");
        authList.add("AdminController.updatePwd");
        at.setAuthList(authList);
        request.getSession().setAttribute(AuthToken.SESSION_NAME, at);
        request.getSession().setAttribute("login_user", u);


        response.sendRedirect("/admin");
        return;


    }
}
