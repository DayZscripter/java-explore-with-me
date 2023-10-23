package ru.practicum.ewm.dto.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.LocationDto;
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
    LocationDto location;

    Boolean paid;

    Integer participantLimit;

    Boolean requestModeration;

    @NotNull
    Long category;

    @NotBlank
    @Length(min = 20, max = 7000)
    String description;

    @NotNull
    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    @EventDateValidator
    LocalDateTime eventDate;


    @NotBlank
    @Length(min = 3, max = 120)
    String title;
}
