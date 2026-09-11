package com.example.todoapp.model

import java.time.LocalDateTime


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
        progress <= 0f -> "To Do"
        progress < 1f -> "In Progress"
        else -> "Completed"
    }
}