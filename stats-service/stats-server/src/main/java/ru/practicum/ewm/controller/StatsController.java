package ru.practicum.ewm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.HitDtoForPost;
import ru.practicum.ewm.StatisticsForDto;
import ru.practicum.ewm.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {

    private final StatsService service;

    private final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHit(@Valid @RequestBody HitDtoForPost hitDto) {
        log.info("Запрос на сохранение статистики");
        service.createHit(hitDto);
    }

    /**
     Получение статистики.

     @param start   Начальная дата и время.
     @param end     Конечная дата и время.
     @param uris    Список URI (необязательный).
     @param unique  Флаг уникальной статистики.
     @return Список статистики.
     */
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsForDto> getStats(
            @RequestParam @DateTimeFormat(pattern = dateTimePattern) LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = dateTimePattern) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique
    ) {
        log.info("Запрос на получение статистики");
        return service.getStatistics(start, end, uris, unique);
    }
}
