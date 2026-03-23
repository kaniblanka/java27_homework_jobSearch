package kg.attractor.jobsearch.dto;

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
    private Long applicantId;
    private String name;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private List<EducationInfo> educationInfoList;
    private List<WorkExperienceInfo> workExperienceInfoList;
}