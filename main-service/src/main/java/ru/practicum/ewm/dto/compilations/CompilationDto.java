package ru.practicum.ewm.dto.compilations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.events.EventShortDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    private List<EventShortDto> events;

    private Boolean pinned;

    private Long id;

    private String title;
}