package com.example.todoapp.ui.screen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.Project
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
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonTopBar(title = "Add Project", onBack = onBack, onNotificationClick = {})
        }
    ) {
        innerPadding -> AddTaskContent(
        viewModel = viewModel,
        onBack= onBack,
        modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AddTaskContent(
    viewModel: TodoViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = viewModel.categories
    var selectedCategory by remember {
        mutableStateOf(categories.firstOrNull() ?: categories[0])
    }

    var projectName by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var startDate by remember {
        mutableStateOf(LocalDate.now())
    }

    var endDate by remember {
        mutableStateOf(LocalDate.now())
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
    ) {
        TaskGroupDropdown(
            categories=categories,
             selectedGroup = selectedCategory,
            onClick = {category->
                selectedCategory = category
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(12.dp))
        TaskNameInputCard(
            taskName=projectName,
            onChange = {name-> projectName = name},
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(12.dp))
        TaskDescriptionInputCard(
            description = description,
            onChange = {value-> description = value},
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        DateInputCard(
            title = "Start Date",
            date = startDate,
            onChange = {date-> startDate = date},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(12.dp))
        DateInputCard(
            title = "End Date",
            date = endDate,
            onChange = {date-> endDate = date},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.addProject(
                    Project(
                        id = viewModel.projects.size + 1,
                        name = projectName,
                        description = description,
                        categoryId = selectedCategory.id,
                        startDate = startDate,
                        endDate = endDate
                    )
                )
                onBack()
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6333E8),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Add Project",
                fontSize = 20.sp,
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
fun AddProjectScreenPreview() {
    val viewModel: TodoViewModel = viewModel()
    AddProjectScreen(
        viewModel = viewModel,
        onBack = {}
    )
}