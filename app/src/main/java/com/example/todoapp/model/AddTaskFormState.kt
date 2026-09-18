package com.example.todoapp.model

import java.time.LocalDateTime

data class AddTaskFormState(
    val name: String = "",
    val title: String = "",
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now().plusHours(1),
)