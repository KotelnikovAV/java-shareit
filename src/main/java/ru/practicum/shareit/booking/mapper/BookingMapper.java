package ru.practicum.shareit.booking.mapper;

import org.mapstruct.Mapper;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking bookingDtoToBooking (BookingDto bookingDto); /* на это пока не обращай внимания, по ТЗ 14 спринта
    это пока реализовывать не нужно */
    BookingDto bookingToBookingDto (Booking bookingDto);
}
