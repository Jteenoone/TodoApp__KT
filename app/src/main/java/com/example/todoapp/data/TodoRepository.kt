package com.example.todoapp.data

import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface TodoRepository {
    val tasks: Flow<List<Task>>
    val projects: Flow<List<Project>>
    val categories: Flow<List<Category>>

    fun getTaskById(taskId: Int): Task?
    fun getTasksByProject(projectId: Int): List<Task>
    fun getTasksByDate(date: LocalDate): List<Task>
    fun getProjectById(projectId: Int): Project?
    fun getCategoryById(categoryId: Int): Category?
    fun getCategoryByProject(projectId: Int): Category?
    fun createTask(name: String, title: String, projectId: Int, startDate: LocalDateTime, endDate: LocalDateTime): Task
    fun createProject(name: String, categoryId: Int, description: String, startDate: LocalDate, endDate: LocalDate): Project
    suspend fun addTask(task: Task): Unit
    suspend fun updateTask(updatedTask: Task): Unit
    suspend fun deleteTask(taskId: Int): Unit
    suspend fun addProject(project: Project): Unit
    suspend fun updateProject(updatedProject: Project): Unit
    suspend fun deleteProject(projectId: Int): Unit
    suspend fun addCategory(category: Category): Unit
    suspend fun updateCategory(updatedCategory: Category): Unit
    suspend fun deleteCategory(categoryId: Int):Unit

}