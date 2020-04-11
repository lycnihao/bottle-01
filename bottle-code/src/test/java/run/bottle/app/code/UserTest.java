package run.bottle.app.code;

import cn.hutool.crypto.digest.BCrypt;
import run.bottle.app.code.model.entity.User;
import run.bottle.app.code.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 38707
 * @Date: 2020/4/3 23:09
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void initUser(){
        User user = new User();
        user.setNickname("admin");
        user.setUsername("admin");
        user.setEmail("38707145@qq.com");
        user.setPassword(BCrypt.hashpw("123456"));
        user.setAvatar("/avatar2.jpg");
        user.prePersist();
        userService.create(user);
    }
}
