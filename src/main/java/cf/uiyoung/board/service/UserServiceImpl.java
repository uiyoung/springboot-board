package cf.uiyoung.board.service;

import cf.uiyoung.board.dao.RoleDao;
import cf.uiyoung.board.dao.UserDao;
import cf.uiyoung.board.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    @Transactional
    public User join(String email, String name, String password) {
        if (userDao.getUser(email)!= null) {
            throw new RuntimeException("email already exists");
        }

        User user = userDao.addUser(email, name, password);
        userDao.mappingUserRole(user.getUserId());
        return user;
    }

    @Override
    @Transactional
    public User findUser(String email) {
        User user = userDao.getUser(email);
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        Integer result = userDao.updateUser(user);

        return result;
    }

    @Override
    public Integer deleteUser(Integer userId) {
        Integer result = userDao.deleteUser(userId);
        return result;
    }
}
