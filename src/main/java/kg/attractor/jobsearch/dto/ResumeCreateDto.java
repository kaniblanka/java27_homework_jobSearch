package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCreateDto {

    @NotNull(message = "Applicant is required")
    private Long applicantId;

    @NotBlank(message = "Resume name cannot be empty")
    private String name;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary must be >= 0")
    private Double salary;

    private Boolean isActive;

    private List<EducationInfo> educationInfoList;

    private List<WorkExperienceInfo> workExperienceInfoList;
}