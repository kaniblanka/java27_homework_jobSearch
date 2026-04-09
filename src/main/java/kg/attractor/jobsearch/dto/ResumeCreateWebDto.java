package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCreateWebDto {

    @NotBlank(message = "Resume name cannot be empty")
    private String name;

    @NotNull(message = "Category is required")
    @Min(value = 1, message = "Category must be greater than 0")
    private Long categoryId;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary must be >= 0")
    private Double salary;
}