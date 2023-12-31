package ru.practicum.ewm.service.comment;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.exception.model.ConditionsAreNotMetException;
import ru.practicum.ewm.exception.model.ObjectNotFoundException;
import ru.practicum.ewm.mapper.CommentMapper;
import ru.practicum.ewm.model.Comment;
import ru.practicum.ewm.model.Event;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.enums.EventState;
import ru.practicum.ewm.repository.CommentRepository;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentServiceImpl implements CommentService {

    final CommentRepository repository;

    final UserRepository userRepository;

    final EventRepository eventRepository;

    @Override
    public CommentDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto) {
        User user = getUser(userId);
        Event event = getEvent(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsAreNotMetException("Комментарий можно добавить только к опубликованному событию");
        }
        Comment comment = CommentMapper.toComment(newCommentDto, user, event);
        Comment savedComment = repository.save(comment);
        log.info("Создан комментарий id = {}", savedComment.getId());
        return CommentMapper.toCommentDto(savedComment);
    }

    @Override
    public CommentDto updateCommentById(Long userId, Long commentId, NewCommentDto newCommentDto) {
        getUser(userId);
        Comment comment = getComment(commentId);
        validateAuthor(userId, comment.getAuthor().getId());
        comment.setText(newCommentDto.getText());
        log.info("Обновлен комментарий id = {}", commentId);
        return CommentMapper.toCommentDto(repository.save(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByAuthor(Long userId, Long eventId, Integer from, Integer size) {
        getUser(userId);
        PageRequest page = PageRequest.of(from / size, size);
        List<Comment> comments;
        if (eventId != null) {
            getEvent(eventId);
            comments = repository.findAllByAuthorIdAndEventId(userId, eventId, page);
        } else {
            comments = repository.findAllByAuthorId(userId, page);
        }
        log.info("Получили список комментариев пользователя id = {}", userId);
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentByIdByAuthor(Long userId, Long commentId) {
        Comment comment = getComment(commentId);
        getUser(userId);
        validateAuthor(userId, comment.getAuthor().getId());
        repository.deleteById(commentId);
        log.info("Комментарий id = {} удален", commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByAdmin(Long eventId, Integer from, Integer size) {
        PageRequest page = PageRequest.of(from / size, size);
        List<Comment> comments;
        if (eventId != null) {
            getEvent(eventId);
            comments = repository.findAllByEventId(eventId, page);
        } else {
            comments = repository.findAll(page).getContent();
        }
        log.info("Получили список комментариев в ответ на запрос от администратора");
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentByIdByAdmin(Long commentId) {
        getComment(commentId);
        repository.deleteById(commentId);
        log.info("Комментарий id = {} удален администратором", commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsOfEventByPublic(Long eventId, Integer from, Integer size) {
        getEvent(eventId);
        PageRequest page = PageRequest.of(from / size, size);
        List<Comment> comments = repository.findAllByEventId(eventId, page);
        log.info("Получили список комментариев к событию id = {} в ответ на публичный запрос", eventId);
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getCommentByIdPublic(Long commentId) {
        Comment comment = getComment(commentId);
        log.info("Получили комментарий id = {} в ответ на публичный запрос", commentId);
        return CommentMapper.toCommentDto(comment);
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие с id=" + eventId + " не найдено"));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь" +
                        "с id = " + userId + " не найден"));
    }

    private Comment getComment(Long commentId) {
        return repository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден"));
    }

    private void validateAuthor(Long userId, Long authorId) {
        if (!userId.equals(authorId)) {
            throw new ConditionsAreNotMetException("Пользователь не является автором комментария");
        }
    }
}