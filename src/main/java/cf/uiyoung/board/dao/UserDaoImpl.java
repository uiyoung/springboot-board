package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsertOperations inserUser;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.inserUser = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("user_id");
    }

    @Override
    public User addUser(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRegdate(LocalDateTime.now());
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        Number userId = inserUser.executeAndReturnKey(params);  // insert 를 실행하고 auto increment 된 user_id를 가져온다.
        user.setUserId(userId.intValue());

        return user;
    }

    @Override
    public void mappingUserRole(Integer userId) {
        String sql = "INSERT INTO user_role values(:userId, 1)";
        jdbcTemplate.update(sql, Map.of("userId", userId));
    }

    @Override
    public User getUser(String email) {
        try{
            String sql = "SELECT user_id, email, name, password, regdate from user where email= :email";
            RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
            User user = jdbcTemplate.queryForObject(sql, Map.of("email", email), rowMapper);
            return user;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateUser(User user) {
        String sql = "UPDATE user SET name=:name, password=:password WHERE user_id=:userId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        int result = jdbcTemplate.update(sql, params);

        return result;
    }

    @Override
    public Integer deleteUser(Integer userId) {
        String sql = "DELETE FROM user WHERE user_id=:userId";
        int result = jdbcTemplate.update(sql, Map.of("userId", userId));

        return result;
    }
}
