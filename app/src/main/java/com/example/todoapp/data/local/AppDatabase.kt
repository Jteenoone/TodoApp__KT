package com.example.todoapp.data.local

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.TypeConverters
import com.example.todoapp.data.local.converter.ColorConverters
import com.example.todoapp.data.local.converter.DateConverters
import com.example.todoapp.data.local.dao.CategoryDao
import com.example.todoapp.data.local.dao.ProjectDao
import com.example.todoapp.data.local.dao.TaskDao
import com.example.todoapp.data.local.entity.CategoryEntity
import com.example.todoapp.data.local.entity.ProjectEntity
import com.example.todoapp.data.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class, ProjectEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateConverters::class,
    ColorConverters::class
    )
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private val seedCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val categories = listOf(
                    arrayOf("Công việc", Color(0xFFF478B8).value.toString(), Color(0xFFFFE4F2).value.toString(), "work"),
                    arrayOf("Cá nhân", Color(0xFF5F33E1).value.toString(), Color(0xFFEDE7FF).value.toString(), "person"),
                    arrayOf("Học tập", Color(0xFF4CAF50).value.toString(), Color(0xFFE4F7E7).value.toString(), "school"),
                    arrayOf("Sức khỏe", Color(0xFFFF9800).value.toString(), Color(0xFFFFF1DD).value.toString(), "fitness")
                )

                categories.forEach { values ->
                    db.execSQL(
                        "INSERT INTO categories (name, color, backgroundColor, iconName) VALUES (?, ?, ?, ?)",
                        values
                    )
                }
            }
        }

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_database"
                ).addCallback(seedCallback)
                    .build()
                    .also { instance = it }
            }
    }
}
