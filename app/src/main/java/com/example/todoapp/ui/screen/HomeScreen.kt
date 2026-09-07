package com.example.todoapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.model.Category
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.CardResult
import com.example.todoapp.ui.compose.TaskGroupCard
import com.example.todoapp.ui.compose.TaskProgressCard
import com.example.todoapp.utils.CategorySummaries

@Composable
fun HomeScreen(
    name: String
) {
    Scaffold(
        topBar = {
            HomeTopBar(name = name)
        },
    ) {
        innerPadding ->
        HomeContent(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun HomeTopBar(
    name: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_meo),
            contentDescription = "avatar",
            modifier = Modifier.size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
            )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hello!",
                color = Color.Black,
                fontSize = 12.sp
            )
            Text(
                text = name,
                color=Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
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
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {

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
        )
    )
    val tasks = listOf(
        Task(
            id = 1,
            title = "Design shopping application",
            categoryId = 1,
            progress = 0.85f,
        ),
        Task(
            id = 2,
            title = "Create database",
            categoryId = 1,
            progress = 0.72f
        ),
        Task(
            id = 3,
            title = "Redesign portfolio",
            categoryId = 2,
            progress = 1f
        )
    )

    val categorySummaries = CategorySummaries(categories = categories, tasks = tasks)

    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        CardResult(
            value = 85f,
            onView = {},
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )
        Spacer(modifier= Modifier.height(12.dp))
        Row() {
            Text(
                text = "In Progress",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier= Modifier.size(20.dp).background(color = Color(0xFFEEE9FF), shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "6",
                    color = Color(0xFF5F33E1),
                    fontWeight = FontWeight.Medium
                )
            }

        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tasks) {
                    task->
                val category = categories.find {
                    category -> category.id == task.categoryId
                }
                category?.let {
                    TaskProgressCard(
                        task = task,
                        category = it,
                        modifier = Modifier.width(200.dp).height(110.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row() {
            Text(
                text = "Task Groups",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Box(
                modifier= Modifier.size(20.dp).background(color = Color(0xFFEEE9FF), shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "4",
                    color = Color(0xFF5F33E1),
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(modifier= Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categorySummaries) {
                categorySummary->
                TaskGroupCard(
                    categorySummary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview (
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun  HomeScreenPreview() {
    HomeScreen(name = "Nghiem Viet Duc Toan")
}

