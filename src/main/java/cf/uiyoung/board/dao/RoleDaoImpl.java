package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoleDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addRole(Role role) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(role);
        String sql = "INSERT INTO role VALUES(:roleId, :name)";
        Integer result = jdbcTemplate.update(sql, params);

        return result;
    }

    @Override
    public Role getRole(Integer roleId) {
        String sql = "SELECT role_id, name from role where role_id = :roleId";
        RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);
        Role role = jdbcTemplate.queryForObject(sql, Map.of("roleId", roleId), rowMapper);

        return role;
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        String sql = "DELETE FROM role WHERE role_id = :roleId";
        Integer result = jdbcTemplate.update(sql, Map.of("roleId", roleId));

        return result;
    }
}
