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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.BottomBarItem
import com.example.todoapp.ui.compose.CardResult
import com.example.todoapp.ui.compose.ProjectProgressCard
import com.example.todoapp.ui.compose.TaskGroupCard
import com.example.todoapp.utils.CategorySummaries
import com.example.todoapp.utils.calculateProjectProgress
import com.example.todoapp.viewmodel.TodoViewModel
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    name: String,
    viewModel: TodoViewModel,
) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        HomeTopBar(name = name)
    HomeContent( viewModel = viewModel,
        modifier = Modifier.fillMaxSize()
    )
//    }
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
fun HomeContent(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier) {
    val tasks = viewModel.tasks
    val categories = viewModel.categories
    val projects = viewModel.projects
    val categorySummaries = CategorySummaries(categories = categories,projects=projects, tasks = tasks)
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 100.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item{
            HomeTopBar(name = "Ngiem Toan")
        }
        item {
            CardResult(
                value = 85f,
                onView = {},
                modifier = Modifier.fillMaxWidth().height(150.dp)
            )
        }
        item {
            Row() {
                Text(
                    text = "In Progress",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier.size(20.dp)
                        .background(color = Color(0xFFEEE9FF), shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "6",
                        color = Color(0xFF5F33E1),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(projects) { project ->
                    val category = categories.find { category ->
                        category.id == project.categoryId
                    }
                    category?.let {
                        ProjectProgressCard(
                            project = project,
                            progress = calculateProjectProgress(project.id, tasks),
                            category = it,
                            modifier = Modifier.width(200.dp).height(110.dp)
                        )
                    }
                }
            }
        }
//        Spacer(modifier = Modifier.height(12.dp))
        item {
            Row() {
                Text(
                    text = "Task Groups",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Box(
                    modifier = Modifier.size(20.dp)
                        .background(color = Color(0xFFEEE9FF), shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "4",
                        color = Color(0xFF5F33E1),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
//        Spacer(modifier= Modifier.height(12.dp))

//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
            items(categorySummaries) {
                categorySummary->
                TaskGroupCard(
                    categorySummary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
//        }
    }
}

@Composable
fun HomeBottom(
    selectedIndex: Int = 0,
    onItemClick:(Int) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
  Box(
      modifier = Modifier.fillMaxWidth()
          .height(88.dp)
  )  {
      Surface(
          modifier = Modifier
              .fillMaxWidth()
              .height(64.dp)
              .align(Alignment.BottomCenter),
          color = Color(0xFFEDE7FF),
          shape = RoundedCornerShape(
              topStart = 24.dp,
              topEnd = 24.dp,
          )
      ) {
          Row(
              modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
          ) {
              BottomBarItem(
                  icon = Icons.Default.Home,
                  selected = selectedIndex == 0,
                  onClick = {onItemClick(0)}
              )
              BottomBarItem(
                  icon = Icons.Default.CalendarMonth,
                  selected = selectedIndex == 1,
                  onClick = {onItemClick(1)}
              )
              Spacer(modifier = Modifier.width(56.dp))
              BottomBarItem(
                  icon = Icons.AutoMirrored.Filled.Article,
                  selected = selectedIndex == 2,
                  onClick = {onItemClick(2)}
              )

              BottomBarItem(
                  icon = Icons.Default.Group,
                  selected = selectedIndex == 3,
                  onClick = {onItemClick(3)}
              )
          }
      }
      FloatingActionButton(
          onClick = onAddClick,
          modifier = Modifier.size(56.dp)
              .align(Alignment.TopCenter),
          shape = CircleShape,
          containerColor = Color(0xFF5F33E1),
          contentColor = Color.White,
          elevation = FloatingActionButtonDefaults.elevation(
              defaultElevation = 8.dp
          )
      ) {
          Icon(
             imageVector = Icons.Default.Add,
              contentDescription = "Add task",
              modifier = Modifier.size(30.dp)
          )
      }
  }
}

@Preview (
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun  HomeScreenPreview() {
    val viewModel: TodoViewModel = viewModel()
    HomeScreen( viewModel=viewModel,
        name = "Nghiem Viet Duc Toan"
    )
}

