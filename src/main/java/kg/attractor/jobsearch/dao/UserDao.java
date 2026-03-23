package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.UserMapper;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
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