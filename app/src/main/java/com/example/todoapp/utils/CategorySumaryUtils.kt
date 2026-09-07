package com.example.todoapp.utils

import com.example.todoapp.model.Category
import com.example.todoapp.model.CategorySummary
import com.example.todoapp.model.Task

fun CategorySummaries(
    categories: List<Category>,
    tasks: List<Task>
): List<CategorySummary> {
    return categories.map { category ->
        val categoryTasks = tasks.filter { task ->
            task.categoryId == category.id
        }

        val progress = (category.id *29).toFloat()

        CategorySummary(
            category = category,
            taskCount = categoryTasks.size,
            progress = progress
        )
    }
}