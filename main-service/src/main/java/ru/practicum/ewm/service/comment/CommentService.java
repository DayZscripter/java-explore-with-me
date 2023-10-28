package ru.practicum.ewm.service.comment;

import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;

import java.util.List;

public interface CommentService {

    /**
     * Создает комментарий пользователем к событию.
     *
     * @param userId          идентификатор пользователя
     * @param eventId         идентификатор события
     * @param newCommentDto   данные нового комментария
     * @return созданный комментарий
     */
    CommentDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    /**
     * Обновляет комментарий пользователя по его идентификатору.
     *
     * @param userId         идентификатор пользователя
     * @param commentId      идентификатор комментария
     * @param newCommentDto  новые данные комментария
     * @return обновленный комментарий
     */
    CommentDto updateCommentById(Long userId, Long commentId, NewCommentDto newCommentDto);

    /**
     * Получает список комментариев автора к событию.
     *
     * @param userId   идентификатор пользователя
     * @param eventId   идентификатор события
     * @param from      начальная позиция
     * @param size      количество комментариев
     * @return список комментариев автора к событию
     */
    List<CommentDto> getCommentsByAuthor(Long userId, Long eventId, Integer from, Integer size);

    /**
     * Удаляет комментарий автором по его идентификатору.
     *
     * @param userId     идентификатор пользователя
     * @param commentId  идентификатор комментария
     */
    void deleteCommentByIdByAuthor(Long userId, Long commentId);

    /**
     * Получает список всех комментариев события для администратора.
     *
     * @param eventId  идентификатор события
     * @param from     начальная позиция
     * @param size     количество комментариев
     * @return список всех комментариев события
     */
    List<CommentDto> getAllCommentsByAdmin(Long eventId, Integer from, Integer size);

    /**
     * Удаляет комментарий администратором по его идентификатору.
     *
     * @param commentId  идентификатор комментария
     */
    void deleteCommentByIdByAdmin(Long commentId);

    /**
     * Получает список всех комментариев события для публичного доступа.
     *
     * @param eventId  идентификатор события
     * @param from     начальная позиция
     * @param size     количество комментариев
     * @return список всех комментариев события для публичного доступа
     */
    List<CommentDto> getAllCommentsOfEventByPublic(Long eventId, Integer from, Integer size);

    /**
     * Получает комментарий по его идентификатору для публичного доступа.
     *
     * @param commentId  идентификатор комментария
     * @return комментарий для публичного доступа
     */
    CommentDto getCommentByIdPublic(Long commentId);
}
