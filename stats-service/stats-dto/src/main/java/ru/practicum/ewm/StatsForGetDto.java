package ru.practicum.ewm;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

//Data Transfer Object (DTO) для передачи статистики в ответе.

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatsForGetDto {
    public StatsForGetDto(String app, String uri, Long hits) {
        this.app = app;
        this.uri = uri;
        this.hits = hits;
    }

    String app;
    String uri;
    Long hits;
}
