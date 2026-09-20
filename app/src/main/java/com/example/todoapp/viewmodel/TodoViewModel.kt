package com.example.todoapp.viewmodel


import androidx.compose.ui.graphics.Path.Companion.combine
import androidx.compose.ui.text.style.TextDecoration.Companion.combine
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import com.example.todoapp.model.TodoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TodoViewModel(
    private val repository: TodoRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(TodoUiState())

    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.tasks,
                repository.projects,
                repository.categories
            ) {
                tasks, projects, categories->
                TodoUiState(tasks, projects, categories)
            }.collect { newState->
                _uiState.update { current->
                    current.copy(
                        tasks = newState.tasks,
                        projects = newState.projects,
                        categories = newState.categories,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun createTask(
        name: String,
        title: String,
        projectId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Task {
        return repository.createTask(
            name = name,
            title = title,
            projectId = projectId,
            startDate = startDate,
            endDate = endDate
        )
    }

    fun createProject(
        name: String,
        categoryId: Int,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): Project {
        return repository.createProject(
            name = name,
            categoryId = categoryId,
            description = description,
            startDate = startDate,
            endDate = endDate
        )
    }




    fun updateTask(updatedTask: Task) {
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }


    fun updateTaskProgress(taskId: Int, progress: Float) {
        val task = uiState.value.tasks.find { it.id == taskId }
        if (task != null) {
            viewModelScope.launch {
                val updatedTask = task.copy(progress = progress)
                repository.updateTask(updatedTask)
            }
        }
    }
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    fun addProject(project: Project, onAdded: (Int) -> Unit = {}) {
        viewModelScope.launch {
            onAdded(repository.addProject(project))
        }
    }

    fun updateProject(updatedProject: Project) {
        viewModelScope.launch {
            repository.updateProject(updatedProject)
        }
    }

    fun deleteProject(projectId: Int) {
        viewModelScope.launch {
            repository.deleteProject(projectId)
        }
    }

    fun  addCategory(category: Category) {
        viewModelScope.launch {
            repository.addCategory(category)
        }
    }

    fun updateCategory(updatedCategory: Category) {
        viewModelScope.launch {
            repository.updateCategory(updatedCategory)
        }
    }


    fun deleteCategory(categoryId: Int) {
        viewModelScope.launch {
            repository.deleteCategory(categoryId)
        }
    }

    fun updateDate(date: LocalDate) {
        _uiState.update {
            it.copy(selectedDate = date)
        }
    }
}
