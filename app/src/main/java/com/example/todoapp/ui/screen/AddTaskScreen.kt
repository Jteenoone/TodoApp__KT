package com.example.todoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.todoapp.model.Category
import com.example.todoapp.ui.compose.TaskDescriptionInputCard
import com.example.todoapp.ui.compose.TaskGroupDropdown
import com.example.todoapp.ui.compose.TaskNameInputCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Add Task",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    Box (modifier = Modifier.size(48.dp)){
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Thong bao",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Box(
                            modifier = Modifier.size(6.dp)
                                .offset(x = (-15).dp, y = 12.dp)
                                .align(Alignment.TopEnd)
                                .background(
                                    color = Color(0xFF5F33E1),
                                    shape = CircleShape
                                )

                        )
                    }
                }
            )
        }
    ) {
        innerPadding -> AddTaskContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AddTaskContent(
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        Category(
            id = 1,
            name = "Office Project",
            color = Color(0xFFF478B8),
            backgroundColor = Color(0xFFFFE4F2),
            icon = Icons.Default.Work
        ),
        Category(
            id = 2,
            name = "Personal Project",
            color = Color(0xFF8758F1),
            backgroundColor = Color(0xFFEDE5FF),
            icon = Icons.Default.Person
        ),
        Category(
            id = 3,
            name = "Daily Study",
            color = Color(0xFFFF8845),
            backgroundColor = Color(0xFFFFE8D8),
            icon = Icons.Default.MenuBook
        ),
        Category(
            id = 4,
            name = "Health & Fitness",
            color = Color(0xFF41A865),
            backgroundColor = Color(0xFFDFF5E7),
            icon = Icons.Default.FitnessCenter
        ),
        Category(
            id = 5,
            name = "Shopping",
            color = Color(0xFFFFB300),
            backgroundColor = Color(0xFFFFF2CC),
            icon = Icons.Default.ShoppingCart
        ),
        Category(
            id = 6,
            name = "Home Tasks",
            color = Color(0xFF2196F3),
            backgroundColor = Color(0xFFDDEEFF),
            icon = Icons.Default.Home
        )
    )
    var selectedCategory by remember {
        mutableStateOf(categories.first())
    }

    var taskName by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
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
            taskName=taskName,
            onChange = {name-> taskName = name},
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(12.dp))
        TaskDescriptionInputCard(
            description = description,
            onChange = {value-> description = value},
            modifier= Modifier.fillMaxWidth()
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(onBack = {})
}