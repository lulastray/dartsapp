package com.luciaastray.dartsapp.service.utils;

import com.luciaastray.dartsapp.service.exceptions.ExceptionConstants;
import com.luciaastray.dartsapp.service.exceptions.NotValidUUID;
import org.springframework.util.Assert;

import java.util.UUID;

public class UUIDUtils {

    public final static UUID parseUUID(String id) {
        Assert.notNull(id, ExceptionConstants.REQUIRED_ID);
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new NotValidUUID(id);
        }
    }
}
