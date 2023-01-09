package cf.uiyoung.board.service;

import cf.uiyoung.board.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void joinTest() {
        User user = userService.join("exam@example.com", "test", "1234");
    }

    @Test
    void findUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}