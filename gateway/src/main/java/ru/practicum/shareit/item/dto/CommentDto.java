package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private long id;
    @NotBlank
    private String text;
    private long itemId;
    private String authorName;
    @JsonFormat(pattern = "yyyy-MM-dd, HH:mm:ss")
    private LocalDateTime created;
}
