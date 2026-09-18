package com.example.todoapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.ui.compose.AreaListDateCard
import com.example.todoapp.ui.compose.CommonTopBar
import com.example.todoapp.ui.compose.TaskDetailCard
import com.example.todoapp.viewmodel.TodoViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun CalendarScreen(
    viewModel: TodoViewModel,
    bottomPadding: Dp = 0.dp
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val selectedDate = uiState.value.selectedDate
    
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonTopBar(
                title = "Today's Tasks",
                onBack = {}, // Viết logic quay lại ở đây
                onNotificationClick = {}
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = scaffoldPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Date Picker Area
            AreaListDateCard(
                selectedDate = selectedDate,
                onDateSelected = { date -> viewModel.updateDate(date) },
                modifier = Modifier.height(110.dp).fillMaxWidth()
            )

            // Task List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = bottomPadding + 16.dp // Khớp với BottomBar
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val startDate = selectedDate.atStartOfDay()
                val endDate = selectedDate.atTime(LocalTime.MAX)
                val dailyTasks = uiState.value.tasks.filter { it.startDate in startDate..endDate }
                items(dailyTasks) { item ->
                    val project: Project? = uiState.value.projects.find {
                        it.id == item.projectId
                    }
                    val category: Category? = uiState.value.categories.find{it.id == (project?.categoryId ?: 0)}

                    if (category != null && project != null) {
                        TaskDetailCard(
                            task = item,
                            category = category,
                            project = project,
                            onUpdateProgress = { taskId, progress -> viewModel.updateTaskProgress(taskId, progress) }
                        )
                    }
                }
            }
        }
    }
}
