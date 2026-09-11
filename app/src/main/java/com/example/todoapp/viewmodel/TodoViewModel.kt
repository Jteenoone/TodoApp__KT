package com.example.todoapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import java.time.LocalDate
import java.time.LocalDateTime

class TodoViewModel: ViewModel() {
    var tasks by mutableStateOf<List<Task>>(listOf(
        Task(
            id = 1,
            name = "UI Design",
            title = "Design the Home Screen UI",
            progress = 0.8f,
            projectId = 1,
            startDate = LocalDateTime.now().minusDays(1),
            endDate = LocalDateTime.now().plusDays(2)
        ),
        Task(
            id = 2,
            name = "API Setup",
            title = "Initialize Retrofit and Service",
            progress = 0.3f,
            projectId = 1,
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now().plusDays(3)
        ),
        Task(
            id = 3,
            name = "Study Coroutines",
            title = "Read about launch vs async",
            progress = 1.0f,
            projectId = 2,
            startDate = LocalDateTime.now().minusDays(3),
            endDate = LocalDateTime.now().minusDays(2)
        ),
        Task(
            id = 4,
            name = "Exercise",
            title = "Morning Gym Session",
            progress = 0.0f,
            projectId = 3,
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now().plusHours(2)
        )
    ))
    private set

    var projects by mutableStateOf<List<Project>>(listOf(
        Project(
            id = 1,
            name = "Office Mobile App",
            categoryId = 1,
            description = "Developing a modern Todo application",
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusDays(30)
        ),
        Project(
            id = 2,
            name = "Learning Kotlin",
            categoryId = 3,
            description = "Course on advanced Kotlin features",
            startDate = LocalDate.now().minusDays(10),
            endDate = LocalDate.now().plusDays(20)
        ),
        Project(
            id = 3,
            name = "Health & Fitness",
            categoryId = 4,
            description = "Training plan for next 3 months",
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusDays(90)
        )
    ))
    private set

    var categories by mutableStateOf<List<Category>>(value = listOf(
        Category(
            id = 1,
            name = "Office Project",
            color = Color(0xFFF478B8),
            backgroundColor = Color(0xFFFFE4F2),
            icon = Icons.Default.Work
        ),
        Category(
            id = 2,
            name = "Personal Project",
            color = Color(0xFF5F33E1),
            backgroundColor = Color(0xFFEDE7FF),
            icon = Icons.Default.Person
        ),
        Category(
            id = 3,
            name = "Study Project",
            color = Color(0xFF4CAF50),
            backgroundColor = Color(0xFFE4F7E7),
            icon = Icons.Default.School
        ),
        Category(
            id = 4,
            name = "Health Project",
            color = Color(0xFFFF9800),
            backgroundColor = Color(0xFFFFF1DD),
            icon = Icons.Default.FitnessCenter
        )
    ))
    private set

    fun updateTask(updatedTask: Task) {
        tasks =  tasks.map { task->
            if(task.id == updatedTask.id) updatedTask else task
        }
    }

    fun updateProject(updatedProject: Project) {
        projects = projects.map{ project ->
            if(project.id == updatedProject.id) updatedProject else project
        }
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun addProject(project: Project) {
        projects = projects + project
    }

    fun addCategory(category: Category) {
        categories = categories + category
    }

    fun deleteTask(taskId: Int) {
        tasks = tasks.filter { task -> task.id != taskId }
    }
    fun deleteProject(projectId: Int) {
        projects = projects.filter { project -> project.id != projectId }
    }

    // get Task
    fun getTaskByProject(projectId: Int): List<Task> {
        return tasks.filter {
            it.projectId == projectId
        }
    }

    fun getTaskById(taskId: Int): Task? {
        val result = tasks.find {  it.id == taskId }

        return result
    }

    fun getTaskByDate(date: LocalDate): List<Task> {
        val dateTime = date.atStartOfDay()
        return tasks.filter { task ->
            task.startDate.isBefore(dateTime) && task.endDate.isAfter(dateTime)
        }
    }

    // getProject

    fun getProjectById(projectId: Int): Project? {
        return projects.find{ it.id == projectId }
    }

    fun getProjectByCategory(categoryId: Int): List<Project> {
        return projects.filter { project ->
            project.categoryId == categoryId
        }
    }

    fun getProjectByDate(date: LocalDate): List<Project> {
        return projects.filter { project ->
            project.startDate.isBefore(date) && project.endDate.isAfter(date)
        }
    }

    // getCategory
    fun getCategoryById(categoryId: Int): Category? {
        return categories.find { it.id == categoryId }
    }

}
