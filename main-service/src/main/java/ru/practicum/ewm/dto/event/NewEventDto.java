package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.location.LocationDto;
import ru.practicum.ewm.validation.EventDateValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ru.practicum.ewm.ConstantsForDto.DATE_TIME_FORMAT_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {

    @NotBlank
    @Length(min = 20, max = 2000)
    String annotation;

    @NotNull
    Long category;

    @NotBlank
    @Length(min = 20, max = 7000)
    String description;

    @NotNull
    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    @EventDateValidator
    LocalDateTime eventDate;

    @NotNull
    LocationDto location;

    Boolean paid;

    Boolean requestModeration;

    Integer participantLimit;

    @NotBlank
    @Length(min = 3, max = 120)
    String title;
}
