package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.StatsForGetDto;
import ru.practicum.ewm.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {

    // Получение статистики по датам и URI, с группировкой по приложению и URI
    @Query("select new ru.practicum.ewm.StatsForGetDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatsForGetDto> getAllByTimestampAndUri(LocalDateTime start, LocalDateTime end, List<String> uris);

    // Получение уникальной статистики по датам и URI, с группировкой по приложению и URI
    @Query("select new ru.practicum.ewm.StatsForGetDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatsForGetDto> getAllByTimestampAndUriUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    // Получение общей статистики по датам, с группировкой по приложению и URI
    @Query("select new ru.practicum.ewm.StatsForGetDto(h.app, h.uri, count(h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatsForGetDto> getAllByTimestamp(LocalDateTime start, LocalDateTime end);

    // Получение уникальной статистики по датам, с группировкой по приложению и URI
    @Query("select new ru.practicum.ewm.StatsForGetDto(h.app, h.uri, count(distinct h.ip)) " +
            "from Hit as h where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatsForGetDto> getAllByTimestampUnique(LocalDateTime start, LocalDateTime end);
}
