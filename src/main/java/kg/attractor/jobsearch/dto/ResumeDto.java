package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {

    private Long id;

    @NotNull(message = "Applicant id cannot be null")
    private Long applicantId;

    @NotBlank(message = "Resume name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Category id cannot be null")
    private Long categoryId;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}