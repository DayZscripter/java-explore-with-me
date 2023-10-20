package ru.practicum.ewm.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hits")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "app", nullable = false) //Идентификатор сервиса для которого записывается инфа
    String app;

    @Column(name = "uri", nullable = false) //url для которого был осуществлен запрос
    String uri;

    @Column(name = "ip", nullable = false) //ip адрес пользователя, осуществившего запрос
    String ip;

    @Column(name = "created", nullable = false) //Дата и время, когда был совершен запрос к эндпоинту
    LocalDateTime timestamp;
}