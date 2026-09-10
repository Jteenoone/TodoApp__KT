package com.example.todoapp.utils

import com.example.todoapp.model.Category
import com.example.todoapp.model.CategorySummary
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task

fun CategorySummaries(
    categories: List<Category>,
    projects: List<Project>,
    tasks: List<Task>
): List<CategorySummary> {
    return categories.map { category ->
        val categoryProject = projects.find { project ->
            project.categoryId == category.id
        }

        val categoryTask = tasks.filter { task ->
            task.projectId == categoryProject?.id
        }

        val progress = (category.id *17).toFloat()

        CategorySummary(
            category = category,
            taskCount = categoryTask.size,
            progress = progress
        )
    }
}