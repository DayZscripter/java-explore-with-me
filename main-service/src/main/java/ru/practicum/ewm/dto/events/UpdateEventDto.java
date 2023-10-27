package ru.practicum.ewm.dto.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.dto.LocationDto;
import ru.practicum.ewm.model.enums.StateAction;
import ru.practicum.ewm.validation.EventDateValidator;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ru.practicum.ewm.ConstantsForDto.DATE_TIME_FORMAT_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventDto {

    @Size(min = 20, max = 2000)
    String annotation;

    Long category;

    @Size(min = 20, max = 7000)
    String description;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    @EventDateValidator
    LocalDateTime eventDate;

    LocationDto location;

    Boolean paid;

    Integer participantLimit;

    Boolean requestModeration;

    StateAction stateAction;

    @Size(min = 3, max = 120)
    String title;
}
