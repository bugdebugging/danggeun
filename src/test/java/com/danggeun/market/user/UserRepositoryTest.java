package com.danggeun.market.user;

import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserImage;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void User_저장() {
        User user = new User("test@domain.com", "pw", "name", "999-9999-999", "nickname");
        user.changeProfile(UserImage.of("https://s3.ap-northeast-2.amazonaws.com/danggeun/user/4/1"), "modified nickname");
        User savedUser = userRepository.save(user);

        assertEquals(user.getUserImage(),savedUser.getUserImage());
        assertEquals(user.getName(),savedUser.getName());
        assertEquals("modified nickname",savedUser.getNickname());
        assertEquals(user.getEmail(),savedUser.getEmail());
        assertEquals(user.getPassword(),savedUser.getPassword());
        assertEquals(user.getPhone(),savedUser.getPhone());
    }
}
