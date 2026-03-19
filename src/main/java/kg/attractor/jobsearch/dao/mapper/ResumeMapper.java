package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResumeMapper implements RowMapper<Resume> {
    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume r = new Resume();
        r.setId(rs.getLong("id"));
        r.setTitle(rs.getString("title"));
        r.setDescription(rs.getString("description"));
        r.setUserId(rs.getLong("user_id"));
        r.setCategoryId(rs.getLong("category_id"));
        return r;
    }
}