package com.example.todoapp.data

import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class FakeTodoRepository : TodoRepository {
    private val _tasksFlow = MutableStateFlow(FakeTodoData.tasks)
    private val _projectsFlow = MutableStateFlow(FakeTodoData.projects)
    private val _categoriesFlow = MutableStateFlow(FakeTodoData.categories)
    override  val tasks: Flow<List<Task>> = _tasksFlow.asStateFlow()
    override  val projects: Flow<List<Project>> = _projectsFlow.asStateFlow()

    override val categories: Flow<List<Category>> = _categoriesFlow.asStateFlow()

    override fun createTask(
        name: String,
        title: String,
        projectId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime): Task {
        val newTask = Task(
            id = (_tasksFlow.value.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            title = title,
            projectId = projectId,
            startDate = startDate,
            endDate = endDate,
            progress = 0f
        )
        return newTask
    }

    override fun createProject(
        name: String,
        categoryId: Int,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): Project {
        val newProject = Project(
            id = (_projectsFlow.value.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            categoryId = categoryId,
            description = description,
            startDate = startDate,
            endDate = endDate
        )
        return newProject
    }
    override fun getCategoryById(categoryId: Int): Flow<Category?> =
        _categoriesFlow.map { categories -> categories.find { it.id == categoryId } }

    override fun getCategoryByProject(projectId: Int): Flow<Category?> =
        combine(_projectsFlow, _categoriesFlow) { projects, categories ->
            val categoryId = projects.find { it.id == projectId }?.categoryId
            categories.find { it.id == categoryId }
        }

    override fun getTasksByDate(date: LocalDate): Flow<List<Task>> {
        val startDate = date.atStartOfDay()
        val endDate = date.atTime(LocalTime.MAX)

        return _tasksFlow.map { tasks ->
            tasks.filter {
                it.startDate <= endDate && it.endDate >= startDate
            }.sortedBy { it.startDate }
        }
    }

    override fun getTaskById(taskId: Int): Flow<Task?> =
        _tasksFlow.map { tasks -> tasks.find { it.id == taskId } }

    override fun getTasksByProject(projectId: Int): Flow<List<Task>> =
        _tasksFlow.map { tasks -> tasks.filter { it.projectId == projectId } }

    override suspend fun addTask(task: Task): Int {
        _tasksFlow.update {
                current -> current + task
        }
        return task.id
    }

    override suspend fun updateTask(updatedTask: Task) {
        _tasksFlow.update {
            current -> current.map {if(it.id == updatedTask.id) updatedTask else it}
        }
    }


    override suspend fun deleteTask(taskId: Int) {
        _tasksFlow.value = _tasksFlow.value.filter { it.id != taskId }
    }

    override fun getProjectById(projectId: Int): Flow<Project?> =
        _projectsFlow.map { projects -> projects.find { it.id == projectId } }

    override suspend fun addProject(project: Project): Int {
        _projectsFlow.value += project
        return project.id
    }

    override suspend fun updateProject(updatedProject: Project) {
        _projectsFlow.value = _projectsFlow.value.map {
            if(it.id == updatedProject.id) updatedProject else it
        }
    }

    override suspend fun deleteProject(projectId: Int) {
        _projectsFlow.value = _projectsFlow.value.filter { it.id != projectId }
    }
    override suspend fun addCategory(category: Category): Int {
        _categoriesFlow.value += category
        return category.id
    }

    override suspend fun deleteCategory(categoryId: Int) {
        _categoriesFlow.value = _categoriesFlow.value.filter { it.id != categoryId }
    }

    override suspend fun updateCategory(updatedCategory: Category) {
        _categoriesFlow.value = _categoriesFlow.value.map{
            if(it.id == updatedCategory.id) updatedCategory else it
        }
    }
}
