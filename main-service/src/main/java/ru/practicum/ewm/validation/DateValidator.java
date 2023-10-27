package ru.practicum.ewm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<EventDateValidator, LocalDateTime> {

    @Override
    public void initialize(EventDateValidator constraintAnnotation) {
        // Метод инициализации, может быть пустым
    }

    @Override
    public boolean isValid(LocalDateTime eventDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime now = LocalDateTime.now();

        if (eventDate != null) {
            // Проверяем, что дата события находится в будущем
            // и оставляем запас хотя бы 2 часа относительно текущего времени
            return eventDate.isAfter(now.plusHours(2L));
        } else {
            // Если дата не указана (null), считаем валидным
            return true;
        }
    }
}
