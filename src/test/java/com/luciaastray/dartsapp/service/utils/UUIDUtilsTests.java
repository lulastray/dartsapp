package com.luciaastray.dartsapp.service.utils;

import com.luciaastray.dartsapp.service.exceptions.ExceptionConstants;
import com.luciaastray.dartsapp.service.exceptions.NotValidUUID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UUIDUtilsTests {

    @Test
    void should_parse_from_string_to_uuid() {

        // GIVEN (Condiciones de partida)
        String id = "83dd97c2-64af-4a82-b8af-ffa3d00f51ce";

        // WHEN (Lo que sucede)
        UUID actual = UUIDUtils.parseUUID(id);

        // THEN (Qué resultado espero)
        assertNotNull(actual);
    }

    @Test
    void should_fail_parse_if_string_is_null() {

        // GIVEN
        String id = null;

        // WHEN && THEN
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            UUID actual = UUIDUtils.parseUUID(id);
        });
        assertEquals(ExceptionConstants.REQUIRED_ID, e.getMessage());
    }

    @Test
    void should_fail_parse_if_string_is_not_valid() {

        // GIVEN
        String id = "wrong";

        // WHEN && THEN
        Exception e = assertThrows(NotValidUUID.class, () -> {
            UUID actual = UUIDUtils.parseUUID(id);
        });
        assertEquals(String.format(ExceptionConstants.NOT_VALID_UUID, id), e.getMessage());
    }
}
