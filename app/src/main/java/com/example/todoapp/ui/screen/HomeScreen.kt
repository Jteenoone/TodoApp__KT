package com.example.todoapp.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.ui.compose.CardResult
import com.example.todoapp.ui.compose.ProjectProgressCard
import com.example.todoapp.ui.compose.TaskGroupCard
import com.example.todoapp.utils.CategorySummaries
import com.example.todoapp.utils.calculateProjectProgress
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun HomeScreen(
    name: String,
    viewModel: TodoViewModel,
    bottomPadding: Dp = 0.dp,
    onClickCardProject: (Int) -> Unit,
) {
    HomeContent(
        viewModel = viewModel,
        bottomPadding = bottomPadding,
        onClickCardProject=onClickCardProject,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun HomeTopBar(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_meo),
            contentDescription = "avatar",
            modifier = Modifier.size(50.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Hello!", color = Color.Black, fontSize = 12.sp)
            Text(text = name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Box(modifier = Modifier.size(48.dp)) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notify", modifier = Modifier.size(28.dp))
            }
            Box(modifier = Modifier.size(6.dp).offset(x = (-15).dp, y = 12.dp).align(Alignment.TopEnd).background(color = Color(0xFF5F33E1), shape = CircleShape))
        }
    }
}

@Composable
fun HomeContent(
    viewModel: TodoViewModel,
    bottomPadding: Dp,
    onClickCardProject: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tasks = uiState.tasks
    val categories = uiState.categories
    val projects = uiState.projects
    val categorySummaries = CategorySummaries(categories, projects, tasks)
    
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = bottomPadding + 16.dp // Sử dụng bottomPadding ở đây
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { HomeTopBar(name = "Nghiem Toan") }
        item {
            CardResult(
                value = 85f,
                onView = {},
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(horizontal = 16.dp)
            )
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = "In Progress", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(20.dp).background(color = Color(0xFFEEE9FF), shape = CircleShape), contentAlignment = Alignment.Center) {
                    Text(text = projects.size.toString(), color = Color(0xFF5F33E1), fontWeight = FontWeight.Medium)
                }
            }
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(projects) { project ->
                    val category = categories.find { it.id == project.categoryId }
                    category?.let {
                        ProjectProgressCard(
                            project = project,
                            progress = calculateProjectProgress(project.id, tasks),
                            category = it,
                            onClick= { onClickCardProject(project.id) },
                            modifier = Modifier.width(200.dp).height(110.dp)
                        )
                    }
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Task Groups", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(20.dp).background(color = Color(0xFFEEE9FF), shape = CircleShape), contentAlignment = Alignment.Center) {
                    Text(text = categories.size.toString(), color = Color(0xFF5F33E1), fontWeight = FontWeight.Medium)
                }
            }
        }
        items(categorySummaries) { summary ->
            TaskGroupCard(summary, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(name = "Nghiem Toan",
    onClickCardProject = {},
        viewModel = viewModel())
}
