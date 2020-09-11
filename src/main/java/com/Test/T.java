package com.Test;


import com.zslin.RootApplication;
import com.zslin.basic.model.User;
import com.zslin.basic.service.IUserService;
import javafx.application.Application;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.zslin.basic.service.IUserService;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




@SpringBootTest()
public class T {

   @Test
    public void md5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("admin".getBytes());
        md.update("123".getBytes());

        System.out.println(new BigInteger(1, md.digest()).toString(16));

        return ;
    }

    @Autowired
    IUserService userService;

    @Test
    public void find() throws NoSuchAlgorithmException {

        User user=userService.findByUsername("admin");

        System.out.println(user.toString());

        return ;
    }
}


