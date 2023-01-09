package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Role;

public interface RoleDao {
    Integer addRole(Role role);
    Role getRole(Integer roleId);
    Integer deleteRole(Integer roleId);
}
