package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(min = 2, max = 50)
    private String surname;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be positive")
    private Integer age;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private String phoneNumber;

    private String avatar;

    @NotBlank(message = "Account type cannot be empty")
    private String accountType;
}