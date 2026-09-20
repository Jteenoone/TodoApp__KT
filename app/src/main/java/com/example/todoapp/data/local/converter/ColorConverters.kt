package com.example.todoapp.data.local.converter

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverters {

    @TypeConverter
    fun fromColor(color: Color?): Long? {
        return  color?.value?.toLong()
    }

    fun toColor(value: Long?): Color? {
        return value?.let{
            Color(it.toULong())
        }
    }
}