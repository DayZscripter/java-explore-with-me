package ru.practicum.ewm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults; // Добавлена аннотация @FieldDefaults
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


//Data Transfer Object (DTO) для передачи данных о хите в запросе.

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class HitForPostDto {
    Long id;

    @NotBlank
    String app;

    @NotBlank
    String uri;

    @NotBlank
    String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
}
