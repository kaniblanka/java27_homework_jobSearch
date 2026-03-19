package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class VacancyMapper implements RowMapper<Vacancy> {

    @Override
    public Vacancy mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vacancy v = new Vacancy();

        v.setId(rs.getLong("id"));
        v.setName(rs.getString("name"));
        v.setDescription(rs.getString("description"));

        v.setCategoryId(rs.getLong("category_id"));
        v.setSalary(rs.getDouble("salary"));

        v.setExpFrom(rs.getInt("exp_from"));
        v.setExpTo(rs.getInt("exp_to"));

        v.setIsActive(rs.getBoolean("is_active"));

        v.setAuthorId(rs.getLong("author_id"));

        v.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        v.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());

        return v;
    }
}