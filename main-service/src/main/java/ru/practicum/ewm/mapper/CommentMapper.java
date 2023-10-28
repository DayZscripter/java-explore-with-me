package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.model.Comment;
import ru.practicum.ewm.model.Event;
import ru.practicum.ewm.model.User;

import java.time.LocalDateTime;

public class CommentMapper {

    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthorName(UserMapper.toUserShortDto(comment.getAuthor()));
        commentDto.setPublishedOn(comment.getPublishedOn());
        commentDto.setEventId(comment.getEvent().getId());
        return commentDto;
    }

    public static Comment toComment(NewCommentDto dto, User author, Event event) {
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setAuthor(author);
        comment.setEvent(event);
        comment.setPublishedOn(LocalDateTime.now());
        return comment;
    }
}
