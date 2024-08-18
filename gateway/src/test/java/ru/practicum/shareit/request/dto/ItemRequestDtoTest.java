package ru.practicum.shareit.request.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestDtoTest {
    private final JacksonTester<ItemRequestDto> json;

    @Test
    void testResponseBookingDto() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2000, 10, 20, 10, 20,
                10, 10);
        ItemRequestDto itemRequestDto = ItemRequestDto.builder()
                .id(1L)
                .description("qwer")
                .created(localDateTime)
                .build();

        JsonContent<ItemRequestDto> result = json.write(itemRequestDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.created")
                .isEqualTo("2000-10-20, 10:20:10");
        assertThat(result).extractingJsonPathStringValue("$.description").isEqualTo("qwer");
    }

}