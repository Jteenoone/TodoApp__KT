package com.example.todoapp.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime

class DateConverters {
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String?  {
        return value?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let{
            LocalDateTime.parse(value)
        }
    }

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(value)
        }
    }
}