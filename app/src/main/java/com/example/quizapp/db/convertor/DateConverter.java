package com.example.quizapp.db.convertor;


import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toRaw(@Nullable Date date){
        return date.getTime();
    }

    @TypeConverter
    public static Date fromRaw(@Nullable Long timestamp){
        if (timestamp == null) return null;

        return new Date(timestamp);
    }

}
