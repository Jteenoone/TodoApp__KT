package com.example.todoapp.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED,
    PAUSE,
    CANCEL
}

data class Task(
    val id: Int,
    val name: String,
    val title: String,
    val progress: Float,
    val projectId: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
    val status = when{
        progress <= 0f -> TaskStatus.TODO
        progress < 1f -> TaskStatus.IN_PROGRESS
        else -> TaskStatus.COMPLETED
    }
}

fun TaskStatus.label(): String = when(this) {
    TaskStatus.TODO -> "To do"
    TaskStatus.IN_PROGRESS -> "In progress"
    TaskStatus.COMPLETED -> "Completed"
    TaskStatus.PAUSE -> "Pause"
    TaskStatus.CANCEL -> "Cancel"
}

fun TaskStatus.color(): Color = when(this) {
    TaskStatus.TODO -> Color(0xFF0087FF)
    TaskStatus.IN_PROGRESS -> Color(0xFFFF7D53)
    TaskStatus.COMPLETED -> Color(0xFF5F33E1)
    TaskStatus.PAUSE -> Color(0xFF5F33A3)
    TaskStatus.CANCEL -> Color(0xFF5F33F5)
}

fun TaskStatus.backgroundColor(): Color = when(this) {
    TaskStatus.TODO -> Color(0xFFE7F3FF)
    TaskStatus.IN_PROGRESS -> Color(0xFFFFEFE9)
    TaskStatus.COMPLETED -> Color(0xFFEDE7FF)
    TaskStatus.PAUSE -> Color(0xFFEDE7FA)
    TaskStatus.CANCEL -> Color(0xFFEDE7FB)
}


