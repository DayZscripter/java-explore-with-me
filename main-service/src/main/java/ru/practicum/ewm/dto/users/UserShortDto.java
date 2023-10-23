package ru.practicum.ewm.dto.users;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
    private Long id;

    @NotBlank
    @Length(min = 2, max = 250)
    private String name;
}
