package ru.practicum.ewm.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.service.comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users/{userId}/comments")
@Validated
public class PrivateCommentsController {

    private final CommentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(
            @PathVariable Long userId,
            @RequestParam Long eventId,
            @Valid @RequestBody NewCommentDto newCommentDto
    ) {
        log.info("User with id = {} requests to create a comment for event with id = {}", userId, eventId);
        return service.createComment(userId, eventId, newCommentDto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateCommentById(
            @PathVariable Long userId,
            @PathVariable Long commentId,
            @Valid @RequestBody NewCommentDto newCommentDto
    ) {
        log.info("User with id = {} requests to update a comment with id = {}", userId, commentId);
        return service.updateCommentById(userId, commentId, newCommentDto);
    }

    @GetMapping
    public List<CommentDto> getCommentsByAuthor(
            @PathVariable Long userId,
            @RequestParam(required = false) Long eventId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        log.info("User with id = {} requests to get comments authored by them", userId);
        return service.getCommentsByAuthor(userId, eventId, from, size);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByIdByAuthor(
            @PathVariable Long userId,
            @PathVariable Long commentId
    ) {
        log.info("User with id = {} requests to delete a comment with id = {}", userId, commentId);
        service.deleteCommentByIdByAuthor(userId, commentId);
    }
}
