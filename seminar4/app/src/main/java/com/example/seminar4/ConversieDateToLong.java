package com.example.seminar4;

import androidx.room.TypeConverter;
import java.util.Date;

public class ConversieDateToLong {

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromLong(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
}
