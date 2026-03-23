package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.ResumeMapper;
import kg.attractor.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Resume> findByCategory(Long categoryId) {
        String sql = "select * from resumes where category_id = :categoryId";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("categoryId", categoryId),
                new ResumeMapper()
        );
    }

    public List<Resume> findByApplicantId(Long applicantId) {
        String sql = "select * from resumes where applicant_id = :applicantId";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("applicantId", applicantId),
                new ResumeMapper()
        );
    }
}