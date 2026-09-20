package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): Flow<TaskEntity?>

    @Query("SELECT * FROM tasks WHERE projectId = :projectId")
    fun getTasksByProject(projectId: Int): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE startDate <= :endDate AND endDate >= :startDate ORDER BY startDate")
    fun getTasksByDate(
        startDate: java.time.LocalDateTime,
        endDate: java.time.LocalDateTime
    ): Flow<List<TaskEntity>>

    @Insert
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: Int)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}
