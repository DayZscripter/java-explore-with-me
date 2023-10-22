package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.StatisticsForDto;
import ru.practicum.ewm.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between :start and :end " +
            "and h.uri in :uris " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatisticsForDto> getAllByTimestampAndUri(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris
    );

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between :start and :end " +
            "and h.uri in :uris " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatisticsForDto> getAllByTimestampAndUriUnique(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris
    );

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between :start and :end " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatisticsForDto> getAllByTimestamp(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("select new ru.practicum.ewm.StatisticsForDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between :start and :end " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatisticsForDto> getAllByTimestampUnique(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
