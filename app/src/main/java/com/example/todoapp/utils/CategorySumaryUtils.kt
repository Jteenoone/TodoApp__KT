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

        val progress = categoryTask.sumOf{ task -> task.progress.toDouble() }

        CategorySummary(
            category = category,
            taskCount = categoryTask.size,
            progress = if(categoryTask.isEmpty()) 0f else ((progress / categoryTask.size)*100).toFloat()
        )
    }
}