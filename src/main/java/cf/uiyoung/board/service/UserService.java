package cf.uiyoung.board.service;

import cf.uiyoung.board.dto.User;

public interface UserService {
    User join(String email, String name, String password);
    User findUser(String email);
    Integer updateUser(User user);
    Integer deleteUser(Integer userId);
}
