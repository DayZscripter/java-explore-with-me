package ru.practicum.ewm.mapper;

import ru.practicum.ewm.HitDtoForPost;
import ru.practicum.ewm.model.Hit;

public class HitMapper {

    public static Hit toHit(HitDtoForPost hitDto) {
        Hit hit = new Hit();
        hit.setId(hitDto.getId());
        hit.setApp(hitDto.getApp());
        hit.setUri(hitDto.getUri());
        hit.setIp(hitDto.getIp());
        hit.setTimestamp(hitDto.getTimestamp());
        return hit;
    }

    public static HitDtoForPost toHitDto(Hit hit) {
        return HitDtoForPost.builder()
                .id(hit.getId())
                .app(hit.getApp())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .timestamp(hit.getTimestamp())
                .build();
    }
}
