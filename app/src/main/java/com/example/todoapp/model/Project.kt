package com.example.todoapp.model

import java.time.LocalDate


data class Project(
    val id: Int,
    val name: String,
    val categoryId: Int,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) {

}