package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.VacancyMapper;
import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Vacancy> findAll() {
        String sql = "select * from vacancies";
        return jdbcTemplate.query(sql, new VacancyMapper());
    }

    public Optional<Vacancy> findById(Long id) {
        String sql = "select * from vacancies where id = :id";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource().addValue("id", id),
                                new VacancyMapper()
                        )
                )
        );
    }

    public List<Vacancy> findActive() {
        String sql = "select * from vacancies where is_active = true";
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

    public void create(Vacancy vacancy) {
        String sql = "insert into vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time) " +
                "values(:name, :description, :categoryId, :salary, :expFrom, :expTo, :isActive, :authorId, :createdDate, :updateTime)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", vacancy.getIsActive())
                .addValue("authorId", vacancy.getAuthorId())
                .addValue("createdDate", vacancy.getCreatedDate())
                .addValue("updateTime", vacancy.getUpdateTime());

        jdbcTemplate.update(sql, params);
    }

    public boolean update(Long id, Vacancy vacancy) {
        String sql = "update vacancies set name = :name, description = :description, category_id = :categoryId, " +
                "salary = :salary, exp_from = :expFrom, exp_to = :expTo, is_active = :isActive, " +
                "author_id = :authorId, update_time = :updateTime where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", vacancy.getIsActive())
                .addValue("authorId", vacancy.getAuthorId())
                .addValue("updateTime", vacancy.getUpdateTime());

        return jdbcTemplate.update(sql, params) > 0;
    }

    public boolean deleteById(Long id) {
        String sql = "delete from vacancies where id = :id";

        return jdbcTemplate.update(
                sql,
                new MapSqlParameterSource().addValue("id", id)
        ) > 0;
    }
}