package com.example.todoapp.utils

import com.example.todoapp.model.Task


fun calculateProjectProgress(
    projectId: Int,
    tasks: List<Task>
): Float {
    val projectTasks = tasks.filter { task -> task.projectId == projectId }
    return if(projectTasks.isEmpty()) {
        0f
    }else {
        projectTasks.map{it.progress}
            .average()
            .toFloat()
    }
}