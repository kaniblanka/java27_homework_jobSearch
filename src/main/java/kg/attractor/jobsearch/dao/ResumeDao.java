package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dao.mapper.ResumeMapper;
import kg.attractor.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public Optional<Resume> findById(Long id) {
        String sql = "select * from resumes where id = ?";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new ResumeMapper(), id)
                )
        );
    }

    public List<Resume> findByApplicantId(Long applicantId) {
        String sql = "select * from resumes where applicant_id = :applicantId";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("applicantId", applicantId),
                new ResumeMapper()
        );
    }

    public List<Resume> findByCategory(Long categoryId) {
        String sql = "select * from resumes where category_id = :categoryId";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource().addValue("categoryId", categoryId),
                new ResumeMapper()
        );
    }

    public List<Resume> findAll() {
        String sql = "select * from resumes";

        return jdbcTemplate.query(sql, new ResumeMapper());
    }

    public Long create(Resume resume) {
        String sql = "insert into resumes(applicant_id, name, category_id, salary, is_active, created_date, update_time) " +
                "values (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, resume.getApplicantId());
            ps.setString(2, resume.getName());
            ps.setLong(3, resume.getCategoryId());
            ps.setDouble(4, resume.getSalary());
            ps.setBoolean(5, resume.getIsActive());
            ps.setTimestamp(6, java.sql.Timestamp.valueOf(resume.getCreatedDate()));
            ps.setTimestamp(7, java.sql.Timestamp.valueOf(resume.getUpdateTime()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public boolean update(Long id, Resume resume) {
        String sql = "update resumes set applicant_id = ?, name = ?, category_id = ?, salary = ?, " +
                "is_active = ?, created_date = ?, update_time = ? where id = ?";

        int rows = jdbcTemplate.update(
                sql,
                resume.getApplicantId(),
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.getIsActive(),
                java.sql.Timestamp.valueOf(resume.getCreatedDate()),
                java.sql.Timestamp.valueOf(resume.getUpdateTime()),
                id
        );

        return rows > 0;
    }

    public boolean deleteById(Long id) {
        String sql = "delete from resumes where id = ?";

        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}