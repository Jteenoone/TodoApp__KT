package com.example.todoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.CommonTopBar
import com.example.todoapp.ui.compose.ProjectDateGroupHeader
import com.example.todoapp.ui.compose.ProjectInfoCard
import com.example.todoapp.ui.compose.ProjectTaskItemCard
import com.example.todoapp.utils.Background
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun ProjectDetailScreen(
    projectId: Int,
    viewModel: TodoViewModel,
    onBack: () -> Unit = {},
    onAddTask: () -> Unit = {},
    onTaskClick: (Task) -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    Background {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonTopBar(
                    title = "Project Detail",
                    onBack = onBack,
                    onNotificationClick = onNotificationClick
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = onAddTask,
                    containerColor = Color(0xFF5F33E1),
                    contentColor = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Thêm Task",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    text = {
                        Text(
                            text = "Thêm Task",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        ) { innerPadding ->
            ProjectDetailContent(
                projectId = projectId,
                viewModel = viewModel,
                onTaskClick = onTaskClick,
                onAddTask = onAddTask,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ProjectDetailContent(
    projectId: Int,
    viewModel: TodoViewModel,
    onTaskClick: (Task) -> Unit = {},
    onAddTask: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val project = uiState.value.projects.find { it.id == projectId }
    val category = project?.let { uiState.value.categories.find { category -> category.id == it.categoryId } }
    val tasks = uiState.value.tasks.filter { it.projectId == projectId }

    // Sắp xếp các task theo ngày bắt đầu (startDate tăng dần)
    val sortedTasks = remember(tasks) {
        tasks.sortedBy { it.startDate }
    }

    // Nhóm task theo từng ngày (LocalDate)
    val tasksByDate = remember(sortedTasks) {
        sortedTasks.groupBy { it.startDate.toLocalDate() }
    }

    if (project == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Không tìm thấy thông tin dự án",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 12.dp,
            bottom = 88.dp // Khoảng đệm để không bị FAB che khuất
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. Thẻ thông tin cơ bản của Project
        item {
            ProjectInfoCard(
                project = project,
                category = category,
                tasks = tasks,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 2. Tiêu đề danh sách công việc & badge sắp xếp
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Danh sách công việc",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E2D)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color(0xFFEDE7FF), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sortedTasks.size.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5F33E1)
                        )
                    }
                }

                // Tag thông báo sắp xếp theo thời gian
                Row(
                    modifier = Modifier
                        .background(color = Color(0xFFEDE7FF), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Order",
                        tint = Color(0xFF5F33E1),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Theo thời gian",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF5F33E1)
                    )
                }
            }
        }

        // 3. Danh sách Task hoặc trạng thái trống
        if (sortedTasks.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(color = Color(0xFFEDE7FF), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Assignment,
                                contentDescription = "Empty",
                                tint = Color(0xFF5F33E1),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Chưa có công việc nào",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E2D)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Hãy thêm công việc đầu tiên cho dự án này!",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onAddTask,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F33E1)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Tạo công việc ngay")
                        }
                    }
                }
            }
        } else {
            // Hiển thị danh sách task nhóm theo ngày (Thứ, ngày, tháng)
            tasksByDate.forEach { (date, tasksInDate) ->
                item(key = "header_${date}") {
                    ProjectDateGroupHeader(
                        date = date,
                        taskCount = tasksInDate.size,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                items(tasksInDate, key = { it.id }) { task ->
                    ProjectTaskItemCard(
                        task = task,
                        onClick = { onTaskClick(task) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProjectDetailScreenPreview() {
    val sampleViewModel: TodoViewModel = viewModel()
    ProjectDetailScreen(
        projectId = 1,
        viewModel = sampleViewModel
    )
}