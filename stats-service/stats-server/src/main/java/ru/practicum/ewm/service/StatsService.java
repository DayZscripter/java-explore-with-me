package ru.practicum.ewm.service;

import ru.practicum.ewm.HitDtoForPost;
import ru.practicum.ewm.StatisticsForDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    void createHit(HitDtoForPost hitDto);

    /**
      Получить статистику.

      @param start  Начальная дата и время.
      @param end    Конечная дата и время.
      @param uris   Список URI.
      @param unique Флаг уникальности.
      @return Список статистики.
     */
    List<StatisticsForDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}