package com.zslin.basic.interceptor;


import com.zslin.web.model.Account;
import com.zslin.web.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebLoginDetailService implements UserDetailsService {

    @Autowired
    IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountService.findByEmail(username);

        if(account==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        return account;
    }
}
