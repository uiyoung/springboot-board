package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoleDaoImplTest {
    @Autowired
    RoleDaoImpl roleDaoImpl;

    @Test
    void getRoleTest() {
        Role role = roleDaoImpl.getRole(1);
        assertThat(role.getRoleId()).isEqualTo(1);
    }

    @Test
    void addRoleTest(){
        Role role = new Role();
        role.setRoleId(4);
        role.setName("ROLE_TEST2");

        Integer result = roleDaoImpl.addRole(role);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void deleteRoleTest(){
        Integer result = roleDaoImpl.deleteRole(4);
        assertThat(result).isEqualTo(1);

    }
}