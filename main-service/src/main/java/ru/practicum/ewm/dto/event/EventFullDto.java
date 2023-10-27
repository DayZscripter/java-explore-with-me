package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.location.LocationDto;
import ru.practicum.ewm.dto.user.UserShortDto;
import ru.practicum.ewm.enums.EventState;

import java.time.LocalDateTime;

import static ru.practicum.ewm.ConstantsForDto.DATE_TIME_FORMAT_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime createdOn;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime eventDate;

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime publishedOn;

    String annotation;

    CategoryDto category;

    Integer confirmedRequests;

    String description;

    Long id;

    UserShortDto initiator;

    LocationDto location;

    // исправлен на примитив requestModeration
    boolean requestModeration;

    //(правка ревьюера) paid может иметь лишь два состояния, поэтому лучше изменить тип на примитив. Если значение не будет передано,
    // в таком случае оно будет проинициализировано не null, а false
    boolean paid;

    Integer participantLimit;

    EventState state;

    String title;

    Long views;
}
