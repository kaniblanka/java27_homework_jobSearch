package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VacancyMapper implements RowMapper<Vacancy> {
    @Override
    public Vacancy mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(rs.getLong("id"));
        vacancy.setName(rs.getString("name"));
        vacancy.setDescription(rs.getString("description"));

        Category category = new Category();
        category.setId(rs.getLong("category_id"));
        vacancy.setCategory(category);

        vacancy.setSalary(rs.getDouble("salary"));
        vacancy.setExpFrom(rs.getInt("exp_from"));
        vacancy.setExpTo(rs.getInt("exp_to"));
        vacancy.setIsActive(rs.getBoolean("is_active"));

        User author = new User();
        author.setId(rs.getLong("author_id"));
        vacancy.setAuthor(author);

        Timestamp createdDate = rs.getTimestamp("created_date");
        if (createdDate != null) {
            vacancy.setCreatedDate(createdDate.toLocalDateTime());
        }

        Timestamp updateTime = rs.getTimestamp("update_time");
        if (updateTime != null) {
            vacancy.setUpdateTime(updateTime.toLocalDateTime());
        }

        return vacancy;
    }
}