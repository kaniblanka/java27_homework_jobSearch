package kg.attractor.jobsearch.dao.mapper;

import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespondedApplicantMapper implements RowMapper<RespondedApplicant> {
    @Override
    public RespondedApplicant mapRow(ResultSet rs, int rowNum) throws SQLException {
        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setId(rs.getLong("id"));

        Resume resume = new Resume();
        resume.setId(rs.getLong("resume_id"));
        respondedApplicant.setResume(resume);

        Vacancy vacancy = new Vacancy();
        vacancy.setId(rs.getLong("vacancy_id"));
        respondedApplicant.setVacancy(vacancy);

        respondedApplicant.setConfirmation(rs.getBoolean("confirmation"));

        return respondedApplicant;
    }
}