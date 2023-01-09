package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    void addUser() {
        User user = userDao.addUser("exam@example.com", "exam", "1234");
        assertThat(user.getUserId()).isEqualTo(1);
    }

    @Test
    void getUserTest() {
        User user = userDao.getUser("exam@example.com");
        System.out.println("user = " + user);
        assertThat(user.getUserId()).isEqualTo(2);
    }

    @Test
    void updateUser() {
        User user = userDao.getUser("exam@example.com");
        user.setName("exam2");
        user.setPassword("1111");
        Integer result = userDao.updateUser(user);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void deleteUser() {
        Integer result = userDao.deleteUser(2);
        assertThat(result).isEqualTo(1);
    }
}