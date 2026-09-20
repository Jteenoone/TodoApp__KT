package com.example.todoapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.FakeTodoRepository
import com.example.todoapp.model.AddProjectFormState
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.CommonTopBar
import com.example.todoapp.ui.compose.DateInputCard
import com.example.todoapp.ui.compose.TaskDescriptionInputCard
import com.example.todoapp.ui.compose.TaskGroupDropdown
import com.example.todoapp.ui.compose.TaskNameInputCard
import com.example.todoapp.viewmodel.TodoViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(
    viewModel: TodoViewModel,
    onBack: () -> Unit,
    onNotificationClick: () -> Unit = {},
    onDone: (Int) -> Unit = {}
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonTopBar(title = "Add Project", onBack = onBack, onNotificationClick = onNotificationClick)
        }
    ) {
        innerPadding -> AddProjectContent(
        viewModel = viewModel,
        onDone = onDone,
        modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AddProjectContent(
    viewModel: TodoViewModel,
    onDone: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categories = uiState.categories
    var formState by remember {
        mutableStateOf(
            AddProjectFormState(categoryId = categories.firstOrNull()?.id ?: 0)
        )
    }
    var isNameTouched by rememberSaveable {
        mutableStateOf(false)
    }
    var nameError by rememberSaveable {
        mutableStateOf("")
    }
    var isSaving by rememberSaveable {
        mutableStateOf(false)
    }

    val isFormValid: Boolean = formState.categoryId != 0 &&
            formState.name.isNotBlank() &&
            (formState.endDate.isAfter(formState.startDate)
                    ||formState.endDate.isEqual(formState.startDate))
    val canCreate = isFormValid && !isSaving

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
    ) {
        TaskGroupDropdown(
            categories=categories,
             categoryId = formState.categoryId,
            onClick = {categoryId->
                formState = formState.copy(categoryId = categoryId)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(12.dp))
        TaskNameInputCard(
            taskName=formState.name,
            onChange = {
                name->
                formState = formState.copy(name = name)
                isNameTouched = true
                if(name.isBlank()) nameError = "Tên không được để trống"
                else nameError = ""
                       },
            modifier= Modifier.fillMaxWidth()
        )
        if(nameError != "" && isNameTouched) {
            Text(
                text = nameError,
                color = Color.Red
            )
        }
        Spacer(modifier=Modifier.height(12.dp))
        TaskDescriptionInputCard(
            description = formState.description,
            onChange = {value-> formState = formState.copy(description = value)},
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        DateInputCard(
            title = "Start Date",
            date = formState.startDate,
            onChange = {date-> formState = formState.copy(startDate = date)},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(12.dp))
        DateInputCard(
            title = "End Date",
            date = formState.endDate,
            onChange = {date-> formState = formState.copy(endDate =  date)},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            enabled = canCreate,
            onClick = {
                // Read isSaving directly so a second tap is rejected even before recomposition.
                if (isFormValid && !isSaving) {
                        isSaving = true
                        val project: Project = viewModel.createProject (
                            name = formState.name,
                            description = formState.description,
                            categoryId = formState.categoryId,
                            startDate = formState.startDate,
                            endDate = formState.endDate
                        )
                        viewModel.addProject(project = project) { projectId ->
                            onDone(projectId)
                        }
                    } else {
                        if(formState.name.isBlank()) {
                            nameError = "Tên không được để trống"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6333E8),
                contentColor = if(canCreate)  Color.White else Color.Gray
            )
        ) {
            Text(
                text = if (isSaving) "Creating..." else "Create Project",
                fontSize = 20.sp,
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
fun AddProjectScreenPreview() {
    val viewModel = TodoViewModel(
        repository = FakeTodoRepository()
    )
    AddProjectScreen(
        viewModel = viewModel,
        onBack = {}
    )
}
