package kg.attractor.jobsearch.dao;
import kg.attractor.jobsearch.dao.mapper.VacancyMapper;
import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Vacancy> findAll() {
        String sql = "select * from vacancies";
        return jdbcTemplate.query(sql, new VacancyMapper());
    }

    public List<Vacancy> findByCategory(Long categoryId) {
        String sql = "select * from vacancies where category_id = :categoryId";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("categoryId", categoryId),
                new VacancyMapper()
        );
    }
}