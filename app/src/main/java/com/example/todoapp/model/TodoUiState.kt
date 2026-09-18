package com.example.todoapp.model

import java.time.LocalDate

data class TodoUiState(
    val tasks: List<Task> = emptyList(),
    val projects: List<Project> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val selectedDate: LocalDate = LocalDate.now()
)