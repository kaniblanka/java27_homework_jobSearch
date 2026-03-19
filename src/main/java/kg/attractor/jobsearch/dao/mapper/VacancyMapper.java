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
        v.setName(rs.getString("title"));
        v.setDescription(rs.getString("description"));
        v.setCategoryId(rs.getLong("category_id"));
        return v;
    }
}