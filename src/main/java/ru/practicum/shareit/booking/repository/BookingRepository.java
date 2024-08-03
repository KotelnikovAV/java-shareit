package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBookerId(long bookerId);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where b.id = ?1 and bk.end < ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByBookerAndPast(long bookerId, LocalDateTime localDateTime);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where b.id = ?1 and bk.start > ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByBookerAndFuture(long bookerId, LocalDateTime localDateTime);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where b.id = ?1 and bk.start < ?2 and bk.end > ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByBookerAndCurrent(long bookerId, LocalDateTime localDateTime);

    List<Booking> findByBooker_idAndStatus(long bookerId, String status);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where o.id = ?1 and bk.end < ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByOwnerAndPast(long ownerId, LocalDateTime localDateTime);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where o.id = ?1 and bk.start > ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByOwnerAndFuture(long ownerId, LocalDateTime localDateTime);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where o.id = ?1 and bk.start < ?2 and bk.end > ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByOwnerAndCurrent(long ownerId, LocalDateTime localDateTime);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where o.id = ?1 and bk.status = ?2 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByOwnerAndStatus(long ownerId, String status);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "join i.owner as o " +
            "where o.id = ?1 " +
            "group by bk.start " +
            "order by bk.start desc ")
    List<Booking> findAllBookingByOwner(long ownerId);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "where i.id = ?1 ")
    Booking findByItemId(long itemId);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "where i.id = ?1 and bk.end < ?2 and bk.status = ?3 ")
    Booking findByItemIdPast(long itemId, LocalDateTime localDateTime, Status status);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "where i.id = ?1 and bk.start > ?2 and bk.status = ?3 ")
    Booking findByItemIdFuture(long itemId, LocalDateTime localDateTime, Status status);

    @Query("select bk " +
            "from Booking as bk " +
            "join bk.item as i " +
            "join bk.booker as b " +
            "where b.id = ?1 and bk.end < ?2 ")
    Optional<Booking> findByUserId(long userId, LocalDateTime localDateTime);
}
