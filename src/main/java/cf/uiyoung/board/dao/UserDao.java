package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.User;

public interface UserDao {
    User addUser(String email, String name, String password);
    void mappingUserRole(Long userId);
    User getUser(String email);
    Integer updateUser(User user);
    Integer deleteUser(Integer userId);
}
