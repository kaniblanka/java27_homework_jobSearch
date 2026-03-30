package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));

        int age = rs.getInt("age");
        if (!rs.wasNull()) {
            user.setAge(age);
        }

        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setAvatar(rs.getString("avatar"));
        user.setAccountType(rs.getString("account_type"));
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}