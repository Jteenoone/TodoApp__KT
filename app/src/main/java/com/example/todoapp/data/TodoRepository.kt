package com.example.todoapp.data

import androidx.compose.ui.graphics.Color
import com.example.todoapp.data.local.AppDatabase
import com.example.todoapp.data.local.entity.CategoryEntity
import com.example.todoapp.data.local.entity.ProjectEntity
import com.example.todoapp.data.local.entity.TaskEntity
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import com.example.todoapp.utils.getCategoryIcon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface TodoRepository {
    val tasks: Flow<List<Task>>
    val projects: Flow<List<Project>>
    val categories: Flow<List<Category>>

    fun getTaskById(taskId: Int): Flow<Task?>
    fun getTasksByProject(projectId: Int): Flow<List<Task>>
    fun getTasksByDate(date: LocalDate): Flow<List<Task>>
    fun getProjectById(projectId: Int): Flow<Project?>
    fun getCategoryById(categoryId: Int): Flow<Category?>
    fun getCategoryByProject(projectId: Int): Flow<Category?>
    fun createTask(name: String, title: String, projectId: Int, startDate: LocalDateTime, endDate: LocalDateTime): Task
    fun createProject(name: String, categoryId: Int, description: String, startDate: LocalDate, endDate: LocalDate): Project
    suspend fun addTask(task: Task): Int
    suspend fun updateTask(updatedTask: Task)
    suspend fun deleteTask(taskId: Int)
    suspend fun addProject(project: Project): Int
    suspend fun updateProject(updatedProject: Project)
    suspend fun deleteProject(projectId: Int)
    suspend fun addCategory(category: Category): Int
    suspend fun updateCategory(updatedCategory: Category)
    suspend fun deleteCategory(categoryId: Int)
}

class RoomTodoRepository(
    database: AppDatabase
) : TodoRepository {
    private val taskDao = database.taskDao()
    private val projectDao = database.projectDao()
    private val categoryDao = database.categoryDao()

    override val tasks: Flow<List<Task>> =
        taskDao.getAllTasks().map { entities -> entities.map(TaskEntity::toModel) }

    override val projects: Flow<List<Project>> =
        projectDao.getAllProjects().map { entities -> entities.map(ProjectEntity::toModel) }

    override val categories: Flow<List<Category>> =
        categoryDao.getAllCategories().map { entities -> entities.map(CategoryEntity::toModel) }

    override fun getTaskById(taskId: Int): Flow<Task?> =
        taskDao.getTaskById(taskId).map { it?.toModel() }

    override fun getTasksByProject(projectId: Int): Flow<List<Task>> =
        taskDao.getTasksByProject(projectId).map { entities -> entities.map(TaskEntity::toModel) }

    override fun getTasksByDate(date: LocalDate): Flow<List<Task>> =
        taskDao.getTasksByDate(date.atStartOfDay(), date.atTime(LocalTime.MAX))
            .map { entities -> entities.map(TaskEntity::toModel) }

    override fun getProjectById(projectId: Int): Flow<Project?> =
        projectDao.getProjectById(projectId).map { it?.toModel() }

    override fun getCategoryById(categoryId: Int): Flow<Category?> =
        categoryDao.getCategoryById(categoryId).map { it?.toModel() }

    override fun getCategoryByProject(projectId: Int): Flow<Category?> =
        combine(
            projectDao.getProjectById(projectId),
            categoryDao.getAllCategories()
        ) { project, categoryEntities ->
            categoryEntities.find { it.id == project?.categoryId }?.toModel()
        }

    override fun createTask(
        name: String,
        title: String,
        projectId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ) = Task(
        id = 0,
        name = name,
        title = title,
        progress = 0f,
        projectId = projectId,
        startDate = startDate,
        endDate = endDate
    )

    override fun createProject(
        name: String,
        categoryId: Int,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate
    ) = Project(
        id = 0,
        name = name,
        categoryId = categoryId,
        description = description,
        startDate = startDate,
        endDate = endDate
    )

    override suspend fun addTask(task: Task): Int = taskDao.insertTask(task.toEntity()).toInt()

    override suspend fun updateTask(updatedTask: Task) = taskDao.updateTask(updatedTask.toEntity())

    override suspend fun deleteTask(taskId: Int) = taskDao.deleteTaskById(taskId)

    override suspend fun addProject(project: Project): Int =
        projectDao.insertProject(project.toEntity()).toInt()

    override suspend fun updateProject(updatedProject: Project) =
        projectDao.updateProject(updatedProject.toEntity())

    override suspend fun deleteProject(projectId: Int) = projectDao.deleteProjectById(projectId)

    override suspend fun addCategory(category: Category): Int =
        categoryDao.insertCategory(category.toEntity()).toInt()

    override suspend fun updateCategory(updatedCategory: Category) =
        categoryDao.updateCategory(updatedCategory.toEntity())

    override suspend fun deleteCategory(categoryId: Int) = categoryDao.deleteCategoryById(categoryId)
}

private fun TaskEntity.toModel() = Task(
    id = id,
    name = name,
    title = title,
    progress = progress,
    projectId = projectId,
    startDate = startDate,
    endDate = endDate
)

private fun Task.toEntity() = TaskEntity(
    id = id,
    name = name,
    title = title,
    progress = progress,
    projectId = projectId,
    startDate = startDate,
    endDate = endDate
)

private fun ProjectEntity.toModel() = Project(
    id = id,
    name = name,
    categoryId = categoryId,
    description = description,
    startDate = startDate,
    endDate = endDate
)

private fun Project.toEntity() = ProjectEntity(
    id = id,
    name = name,
    categoryId = categoryId,
    description = description,
    startDate = startDate,
    endDate = endDate
)

private fun CategoryEntity.toModel() = Category(
    id = id,
    name = name,
    color = Color(color.toULong()),
    backgroundColor = Color(backgroundColor.toULong()),
    icon = getCategoryIcon(iconName)
)

private fun Category.toEntity() = CategoryEntity(
    id = id,
    name = name,
    color = color.value.toString(),
    backgroundColor = backgroundColor.value.toString(),
    iconName = icon.name.substringAfterLast('.').lowercase()
)
