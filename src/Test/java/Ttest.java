import com.zslin.RootApplication;
import com.zslin.basic.model.User;
import com.zslin.basic.service.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RootApplication.class)
public class Ttest {

    @Autowired
    IUserService iUserService;

    @Test
    public void t(){


        System.out.println(new BCryptPasswordEncoder().encode("123"));
        return;

    }
}
