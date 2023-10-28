package ru.practicum.ewm.exceptions.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError { //класс для представления ошибки api
    String status;      //статус ошибки
    String reason;      //причина ошибки
    String message;     // сообщение об ошибке
    String timestamp;   //время ошибки
}
