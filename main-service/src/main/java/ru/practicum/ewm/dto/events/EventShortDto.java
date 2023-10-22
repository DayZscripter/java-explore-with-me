package ru.practicum.ewm.dto.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.users.UserShortDto;

import java.time.LocalDateTime;

import static ru.practicum.ewm.ConstantsForDto.DATE_TIME_FORMAT_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortDto {

    String annotation;

    CategoryDto category;

    Integer confirmedRequests;

    Long id;

    UserShortDto initiator;

    Boolean paid;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime eventDate;


    String title;

    Long views;
}
