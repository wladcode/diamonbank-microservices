package com.diamondcode.common.application.service.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDates {

    public static Timestamp fromLocalDateTimeToTimestap(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Timestamp.valueOf(localDateTime);
        }

        return null;
    }

    public static Timestamp fromLocalDateToTimestap(LocalDate  localDate) {
        if (localDate != null) {
            return  Timestamp.valueOf(localDate.atStartOfDay());
        }

        return null;
    }

    public static LocalDateTime fromTimestampToLocalDateTime(Timestamp timestamp) {
        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }

        return null;
    }

    public static LocalDate fromTimestampToLocalDate(Timestamp timestamp) {
        if (timestamp != null) {
            return timestamp.toLocalDateTime().toLocalDate();
        }

        return null;
    }


    public static LocalDate fromStringToLocalDate(String date) {

        if(date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(date, formatter);
        }

        return null;
    }



    public static LocalDateTime fromStringToLocalDateTime(String date) {

        if(date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            return LocalDateTime.parse(date, formatter);
        }

        return null;
    }



}
