package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.RespondedApplicant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespondedApplicantMapper implements RowMapper<RespondedApplicant> {

    @Override
    public RespondedApplicant mapRow(ResultSet rs, int rowNum) throws SQLException {
        RespondedApplicant r = new RespondedApplicant();

        r.setId(rs.getLong("id"));
        r.setResumeId(rs.getLong("resume_id"));
        r.setVacancyId(rs.getLong("vacancy_id"));
        r.setConfirmation(rs.getBoolean("confirmation"));

        return r;
    }
}