package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.UserMapper;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> findById(Long id) {
        String sql = "select * from users where id = :id";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource().addValue("id", id),
                                new UserMapper()
                        )
                )
        );
    }

    public Optional<User> findByEmail(String email) {
        String sql = "select * from users where email = :email";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource().addValue("email", email),
                                new UserMapper()
                        )
                )
        );
    }

    public List<User> findByName(String name) {
        String sql = "select * from users where name = :name";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("name", name),
                new UserMapper()
        );
    }

    public Optional<User> findByPhone(String phoneNumber) {
        String sql = "select * from users where phone_number = :phoneNumber";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource().addValue("phoneNumber", phoneNumber),
                                new UserMapper()
                        )
                )
        );
    }

    public Long create(User user) {
        String sql = "insert into users(name, surname, age, email, password, phone_number, avatar, account_type, enabled) " +
                "values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accountType, :enabled)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("accountType", user.getAccountType())
                .addValue("enabled", user.getEnabled());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void addAuthorityToUser(Long userId, String authority) {
        String sql = "insert into user_authorities(user_id, authority_id) " +
                "values (:userId, (select id from authorities where authority = :authority))";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("authority", authority);

        jdbcTemplate.update(sql, params);
    }

    public boolean updateProfile(Long id, String name, String surname, Integer age,
                                 String email, String phoneNumber, String avatar) {
        String sql = "update users set name = :name, surname = :surname, age = :age, " +
                "email = :email, phone_number = :phoneNumber, avatar = :avatar where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name)
                .addValue("surname", surname)
                .addValue("age", age)
                .addValue("email", email)
                .addValue("phoneNumber", phoneNumber)
                .addValue("avatar", avatar);

        return jdbcTemplate.update(sql, params) > 0;
    }
}