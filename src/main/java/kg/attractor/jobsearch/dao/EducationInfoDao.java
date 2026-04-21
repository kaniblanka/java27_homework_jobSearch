package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(EducationInfo educationInfo) {
        String sql = "insert into education_info(resume_id, institution, program, start_date, end_date, degree) " +
                "values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                educationInfo.getResume().getId(),
                educationInfo.getInstitution(),
                educationInfo.getProgram(),
                educationInfo.getStartDate(),
                educationInfo.getEndDate(),
                educationInfo.getDegree()
        );
    }

    public void deleteByResumeId(Long resumeId) {
        String sql = "delete from education_info where resume_id = ?";
        jdbcTemplate.update(sql, resumeId);
    }
}