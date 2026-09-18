package com.example.todoapp.model

import java.time.LocalDate

data class AddProjectFormState(
    val categoryId: Int = 0,
    val name: String = "",
    val description: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
)