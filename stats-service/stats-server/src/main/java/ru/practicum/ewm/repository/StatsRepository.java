package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.StatisticsForDto;
import ru.practicum.ewm.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatisticsForDto> getAllByTimestampAndUri(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatisticsForDto> getAllByTimestampAndUriUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatisticsForDto> getAllByTimestamp(LocalDateTime start, LocalDateTime end);

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatisticsForDto> getAllByTimestampUnique(LocalDateTime start, LocalDateTime end);
}
