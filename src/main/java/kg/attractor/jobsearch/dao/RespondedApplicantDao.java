package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.RespondedApplicantMapper;
import kg.attractor.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<RespondedApplicant> findByResumeId(Long resumeId) {
        String sql = "select * from responses where resume_id = :resumeId";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("resumeId", resumeId),
                new RespondedApplicantMapper()
        );
    }

    public List<RespondedApplicant> findByVacancyId(Long vacancyId) {
        String sql = "select * from responses where vacancy_id = :vacancyId";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("vacancyId", vacancyId),
                new RespondedApplicantMapper()
        );
    }
}