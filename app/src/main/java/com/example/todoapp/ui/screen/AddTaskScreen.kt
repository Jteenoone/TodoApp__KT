package com.example.todoapp.ui.screen

import android.annotation.SuppressLint
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
import com.example.todoapp.data.FakeTodoRepository
import com.example.todoapp.model.AddTaskFormState
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
    var formState by remember {
        mutableStateOf(AddTaskFormState())
    }
    var isNameTouched by rememberSaveable { mutableStateOf(false) }
    var nameError by rememberSaveable {mutableStateOf("")}

    val canCreate = formState.name.isNotBlank() && formState.endDate.isAfter(formState.startDate)

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        TaskNameInputCard(
            taskName = formState.name,
            onChange = {
                formState = formState.copy(name =it)
                isNameTouched = true
                if(it.isBlank()) nameError = "Tên không được để trống"
                else nameError = ""
                       },
            modifier = Modifier.fillMaxWidth()
        )
        if(nameError != "" && isNameTouched)
            Text(
            text = nameError,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(12.dp))
        TaskDescriptionInputCard(
            description = formState.title,
            onChange = { formState = formState.copy(title = it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        // CHỌN NGÀY VÀ GIỜ BẮT ĐẦU
        DateInputCard(
            title = "Start Date",
            date = formState.startDate.toLocalDate(),
            onChange = { formState =formState.copy(startDate =  LocalDateTime.of(it, formState.startDate.toLocalTime())) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TimeInputCard(
            title = "Start Time",
            time = formState.startDate.toLocalTime(),
            onChange = { formState = formState.copy(startDate= LocalDateTime.of(formState.startDate.toLocalDate(), it)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        // CHỌN NGÀY VÀ GIỜ KẾT THÚC
        DateInputCard(
            title = "End Date",
            date = formState.endDate.toLocalDate(),
            onChange = { formState = formState.copy(endDate = LocalDateTime.of(it, formState.endDate.toLocalTime())) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TimeInputCard(
            title = "End Time",
            time = formState.endDate.toLocalTime(),
            onChange = { formState = formState.copy(endDate = LocalDateTime.of(formState.endDate.toLocalDate(), it)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            enabled = canCreate,
            onClick = {
                if(canCreate) {
                    val task: Task = viewModel.createTask(
                        name = formState.name,
                        title = formState.title,
                        projectId = projectId,
                        startDate = formState.startDate,
                        endDate = formState.endDate
                    )
                    viewModel.addTask(
                        task = task
                    )
                    onBack() // Quay lại sau khi thêm
                } else {
                    if(formState.name.isBlank()) nameError = "Tên không được để trống"
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
                color = if(canCreate) Color.White else Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AddTaskScreenPreview() {
    val viewModel = TodoViewModel(
        repository = FakeTodoRepository()
    )
    AddTaskScreen(
        viewModel = viewModel,
        onBack = {},
        projectId = 1
    )
}
