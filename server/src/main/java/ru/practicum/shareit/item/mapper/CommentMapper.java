package ru.practicum.shareit.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoToComment (CommentDto commentDto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "authorName", source = "author.name")
    CommentDto commentToCommentDto (Comment comment);
}