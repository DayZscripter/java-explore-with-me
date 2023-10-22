package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.HitForPostDto;
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
    public void createHit(HitForPostDto hitDto) {
        Hit hitForSave = HitMapper.toHit(hitDto);
        repository.save(hitForSave);
        log.info("Информация сохранена");
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatisticsForDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (end.isBefore(start)) {
            throw new IncorrectlyMadeRequestException("Значение поля end не может быть раньше значения поля start");
        }

        List<StatisticsForDto> statistics;
        String logMessage;

        if (unique) {
            if (uris != null) {
                statistics = repository.getAllByTimestampAndUriUnique(start, end, uris);
                logMessage = "Получили статистику по заданным uri и уникальным ip";
            } else {
                statistics = repository.getAllByTimestampUnique(start, end);
                logMessage = "Получили статистику по уникальным ip";
            }
        } else {
            if (uris != null) {
                statistics = repository.getAllByTimestampAndUri(start, end, uris);
                logMessage = "Получили общую статистику по заданным uri";
            } else {
                statistics = repository.getAllByTimestamp(start, end);
                logMessage = "Получили общую статистику";
            }
        }

        log.info(logMessage);
        return statistics;
    }
}
