package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(min = 2, max = 50)
    private String surname;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 1, message = "Возраст должен быть не меньше 1")
    @Max(value = 100, message = "Возраст не должен превышать 100")
    private Integer age;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 100, message = "Password must contain at least 4 characters")
    private String password;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private String phoneNumber;

    private String avatar;

    @NotBlank(message = "Account type cannot be empty")
    @Pattern(regexp = "CANDIDATE|EMPLOYER", message = "Account type must be CANDIDATE or EMPLOYER")
    private String accountType;
}