package com.example.todoapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.CommonTopBar
import com.example.todoapp.ui.compose.DateInputCard
import com.example.todoapp.ui.compose.TaskDescriptionInputCard
import com.example.todoapp.ui.compose.TaskNameInputCard
import com.example.todoapp.ui.compose.TimeInputCard
import com.example.todoapp.viewmodel.TodoViewModel
import java.time.LocalDateTime

@Composable
fun AddTaskScreen(
    viewModel: TodoViewModel,
    onBack: () -> Unit,
    projectId: Int
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Add Task",
                onBack = onBack,
                onNotificationClick = {}
            )
        }
    ) { innerPadding ->
        AddTaskContent(
            viewModel = viewModel,
            projectId = projectId,
            onBack = onBack,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AddTaskContent(
    viewModel: TodoViewModel,
    projectId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var title by rememberSaveable { mutableStateOf("") }

    // Dùng remember cho LocalDateTime (vì rememberSaveable cần Saver cho kiểu dữ liệu này)
    var startDate by remember { mutableStateOf(LocalDateTime.now()) }
    var endDate by remember { mutableStateOf(LocalDateTime.now().plusHours(1)) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        TaskNameInputCard(
            taskName = name,
            onChange = { name = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TaskDescriptionInputCard(
            description = title,
            onChange = { title = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        // CHỌN NGÀY VÀ GIỜ BẮT ĐẦU
        DateInputCard(
            title = "Start Date",
            date = startDate.toLocalDate(),
            onChange = { startDate = LocalDateTime.of(it, startDate.toLocalTime()) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TimeInputCard(
            title = "Start Time",
            time = startDate.toLocalTime(),
            onChange = { startDate = LocalDateTime.of(startDate.toLocalDate(), it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        // CHỌN NGÀY VÀ GIỜ KẾT THÚC
        DateInputCard(
            title = "End Date",
            date = endDate.toLocalDate(),
            onChange = { endDate = LocalDateTime.of(it, endDate.toLocalTime()) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TimeInputCard(
            title = "End Time",
            time = endDate.toLocalTime(),
            onChange = { endDate = LocalDateTime.of(endDate.toLocalDate(), it) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (name.isNotBlank()) {
                    viewModel.addTask(
                        Task(
                            id = viewModel.tasks.size + 1,
                            name = name,
                            title = title,
                            startDate = startDate,
                            endDate = endDate,
                            progress = 0f,
                            projectId = projectId
                        )
                    )
                    onBack() // Quay lại sau khi thêm
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5F33E1)
            )
        ) {
            Text(
                text = "Create Task",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AddTaskScreenPreview() {
    val viewModel: TodoViewModel = viewModel()
    AddTaskScreen(
        viewModel = viewModel,
        onBack = {},
        projectId = 1
    )
}
