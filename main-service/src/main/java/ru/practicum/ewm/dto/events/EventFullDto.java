package ru.practicum.ewm.dto.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.LocationDto;
import ru.practicum.ewm.dto.users.UserShortDto;
import ru.practicum.ewm.model.enums.EventState;

import java.time.LocalDateTime;

import static ru.practicum.ewm.ConstantsForDto.DATE_TIME_FORMAT_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {

    String annotation;

    CategoryDto category;

    Integer confirmedRequests;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime createdOn;

    String description;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime eventDate;


    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime publishedOn;

    Boolean requestModeration;

    EventState state;

    Long id;

    UserShortDto initiator;

    LocationDto location;

    Boolean paid;

    Integer participantLimit;

    String title;

    Long views;
}
