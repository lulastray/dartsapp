package com.luciaastray.dartsapp.mother;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class MotherCreator {

    public static final Faker random() {
        return new Faker();
    }

    public static final LocalDateTime randomDate() {
        Instant ins = random().date().past(100, TimeUnit.DAYS).toInstant();
        return LocalDateTime.ofInstant(ins, ZoneId.of("UTC"));
    }
}
