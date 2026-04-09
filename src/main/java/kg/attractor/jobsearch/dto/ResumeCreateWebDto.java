package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCreateWebDto {

    @NotBlank(message = "Название резюме не может быть пустым")
    private String name;

    @NotNull(message = "Категория обязательна")
    @Min(value = 1, message = "Категория должна быть больше 0")
    private Long categoryId;

    @NotNull(message = "Зарплата обязательна")
    @Positive(message = "Зарплата должна быть больше 0")
    private Double salary;
}