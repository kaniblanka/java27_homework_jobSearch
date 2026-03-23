package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(WorkExperienceInfo workExperienceInfo) {
        String sql = "insert into work_experience_info(resume_id, years, company_name, position, responsibilities) " +
                "values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                workExperienceInfo.getResumeId(),
                workExperienceInfo.getYears(),
                workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(),
                workExperienceInfo.getResponsibilities()
        );
    }

    public void deleteByResumeId(Long resumeId) {
        String sql = "delete from work_experience_info where resume_id = ?";
        jdbcTemplate.update(sql, resumeId);
    }
}