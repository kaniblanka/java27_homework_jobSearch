package kg.attractor.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfoDto {
    private String companyName;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String responsibilities;
}