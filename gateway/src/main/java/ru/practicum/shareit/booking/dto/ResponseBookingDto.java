package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseBookingDto {
    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd, HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd, HH:mm:ss")
    private LocalDateTime end;
    private ItemDto item;
    private UserDto booker;
    private Status status;
}
