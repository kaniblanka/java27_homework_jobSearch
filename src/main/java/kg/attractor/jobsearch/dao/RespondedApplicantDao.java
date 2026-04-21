package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.RespondedApplicantMapper;
import kg.attractor.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Long create(RespondedApplicant respondedApplicant) {
        String sql = "insert into responses(resume_id, vacancy_id, confirmation) " +
                "values (:resumeId, :vacancyId, :confirmation)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("resumeId", respondedApplicant.getResume().getId())
                .addValue("vacancyId", respondedApplicant.getVacancy().getId())
                .addValue("confirmation", respondedApplicant.getConfirmation());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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