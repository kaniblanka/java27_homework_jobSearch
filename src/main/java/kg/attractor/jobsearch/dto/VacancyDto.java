package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {

    private Long id;

    @NotBlank(message = "Vacancy name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary must be >= 0")
    private Double salary;

    @NotNull(message = "Experience from is required")
    @PositiveOrZero
    private Integer expFrom;

    @NotNull(message = "Experience to is required")
    @PositiveOrZero
    private Integer expTo;

    private Boolean isActive;

    @NotNull(message = "Author is required")
    private Long authorId;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}