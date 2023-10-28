package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.HitDtoForPost;
import ru.practicum.ewm.StatisticsForDto;
import ru.practicum.ewm.exceptions.IncorrectlyMadeRequestException;
import ru.practicum.ewm.mapper.HitMapper;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;

    @Override
    public void createHit(HitDtoForPost hitDto) {
        Hit hitForSave = HitMapper.toHit(hitDto);
        repository.save(hitForSave);
        log.info("Information saved");
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatisticsForDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (end.isBefore(start)) {
            throw new IncorrectlyMadeRequestException("The 'end' date cannot be earlier than the 'start' date");
        }
        if (unique) {
            if (uris != null) {
                log.info("Received statistics for specified URIs and unique IPs");
                return repository.getAllByTimestampAndUriUnique(start, end, uris);
            } else {
                log.info("Received statistics for unique IPs");
                return repository.getAllByTimestampUnique(start, end);
            }
        } else {
            if (uris != null) {
                log.info("Received general statistics for specified URIs");
                return repository.getAllByTimestampAndUri(start, end, uris);
            } else {
                log.info("Received general statistics");
                return repository.getAllByTimestamp(start, end);
            }
        }
    }
}
