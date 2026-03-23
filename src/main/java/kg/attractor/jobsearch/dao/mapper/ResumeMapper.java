package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ResumeMapper implements RowMapper<Resume> {

    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume resume = new Resume();

        resume.setId(rs.getLong("id"));
        resume.setApplicantId(rs.getLong("applicant_id"));
        resume.setName(rs.getString("name"));
        resume.setCategoryId(rs.getLong("category_id"));
        resume.setSalary(rs.getDouble("salary"));
        resume.setIsActive(rs.getBoolean("is_active"));

        Timestamp createdDate = rs.getTimestamp("created_date");
        if (createdDate != null) {
            resume.setCreatedDate(createdDate.toLocalDateTime());
        }

        Timestamp updateTime = rs.getTimestamp("update_time");
        if (updateTime != null) {
            resume.setUpdateTime(updateTime.toLocalDateTime());
        }

        return resume;
    }
}