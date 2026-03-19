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
}