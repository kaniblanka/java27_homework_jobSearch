package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyCreateWebDto {

    @NotBlank(message = "Название вакансии не может быть пустым")
    private String name;

    @NotBlank(message = "Описание не может быть пустым")
    private String description;

    @NotNull(message = "Категория обязательна")
    @Min(value = 1, message = "Категория должна быть больше 0")
    private Long categoryId;

    @NotNull(message = "Зарплата обязательна")
    @PositiveOrZero(message = "Зарплата не может быть отрицательной")
    private Double salary;

    @NotNull(message = "Опыт от обязателен")
    @Min(value = 0, message = "Опыт не может быть отрицательным")
    @Max(value = 50, message = "Опыт от не может быть больше 50 лет")
    private Integer expFrom;

    @NotNull(message = "Опыт до обязателен")
    @Min(value = 0, message = "Опыт не может быть отрицательным")
    @Max(value = 50, message = "Опыт до не может быть больше 50 лет")
    private Integer expTo;
}