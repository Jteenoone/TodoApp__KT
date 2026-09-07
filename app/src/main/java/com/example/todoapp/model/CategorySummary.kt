package com.example.todoapp.model

data class CategorySummary(
    val category: Category,
    val taskCount: Int,
//    val completedCount: Int,
    val progress: Float,
)