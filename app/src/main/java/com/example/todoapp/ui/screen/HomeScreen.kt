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
import java.time.LocalDate

@Composable
fun HomeScreen(
    name: String
) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        HomeTopBar(name = name)
    HomeContent(modifier = Modifier.fillMaxSize())
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
            color = Color(0xFF5F33E1),
            backgroundColor = Color(0xFFEDE7FF),
            icon = Icons.Default.Person
        ),
        Category(
            id = 3,
            name = "Study Project",
            color = Color(0xFF4CAF50),
            backgroundColor = Color(0xFFE4F7E7),
            icon = Icons.Default.School
        ),
        Category(
            id = 4,
            name = "Health Project",
            color = Color(0xFFFF9800),
            backgroundColor = Color(0xFFFFF1DD),
            icon = Icons.Default.FitnessCenter
        )
    )

    val projects = listOf(
        Project(
            id = 1,
            name = "Shopping Application",
            categoryId = 1,
            description = "Design and develop a mobile shopping application",
            startDate = LocalDate.of(2026, 9, 1),
            endDate = LocalDate.of(2026, 10, 15)
        ),
        Project(
            id = 2,
            name = "Portfolio Website",
            categoryId = 2,
            description = "Create a personal portfolio website",
            startDate = LocalDate.of(2026, 9, 5),
            endDate = LocalDate.of(2026, 9, 30)
        ),
        Project(
            id = 3,
            name = "Android Course",
            categoryId = 3,
            description = "Complete the Jetpack Compose learning course",
            startDate = LocalDate.of(2026, 9, 2),
            endDate = LocalDate.of(2026, 11, 30)
        ),
        Project(
            id = 4,
            name = "Daily Workout",
            categoryId = 4,
            description = "Build and maintain a daily exercise routine",
            startDate = LocalDate.of(2026, 9, 10),
            endDate = LocalDate.of(2026, 12, 31)
        )
    )

    val tasks = listOf(
        // Shopping Application - projectId = 1
        Task(
            id = 1,
            name = "Market Research",
            title = "Research shopping applications",
            progress = 0.85f,
            projectId = 1,
            startDate = LocalDate.of(2026, 9, 1),
            endDate = LocalDate.of(2026, 9, 5)
        ),
        Task(
            id = 2,
            name = "UI Design",
            title = "Design shopping application",
            progress = 0.65f,
            projectId = 1,
            startDate = LocalDate.of(2026, 9, 6),
            endDate = LocalDate.of(2026, 9, 15)
        ),
        Task(
            id = 3,
            name = "Product Screen",
            title = "Create product list screen",
            progress = 0.40f,
            projectId = 1,
            startDate = LocalDate.of(2026, 9, 16),
            endDate = LocalDate.of(2026, 9, 25)
        ),
        Task(
            id = 4,
            name = "Shopping Cart",
            title = "Implement shopping cart feature",
            progress = 0.15f,
            projectId = 1,
            startDate = LocalDate.of(2026, 9, 26),
            endDate = LocalDate.of(2026, 10, 5)
        ),

        // Portfolio Website - projectId = 2
        Task(
            id = 5,
            name = "Wireframe",
            title = "Create portfolio website wireframe",
            progress = 1f,
            projectId = 2,
            startDate = LocalDate.of(2026, 9, 5),
            endDate = LocalDate.of(2026, 9, 8)
        ),
        Task(
            id = 6,
            name = "Home Page",
            title = "Develop portfolio home page",
            progress = 0.75f,
            projectId = 2,
            startDate = LocalDate.of(2026, 9, 9),
            endDate = LocalDate.of(2026, 9, 18)
        ),
        Task(
            id = 7,
            name = "Project Page",
            title = "Add personal projects to portfolio",
            progress = 0.30f,
            projectId = 2,
            startDate = LocalDate.of(2026, 9, 19),
            endDate = LocalDate.of(2026, 9, 30)
        ),

        // Android Course - projectId = 3
        Task(
            id = 8,
            name = "Kotlin Basics",
            title = "Complete Kotlin fundamentals",
            progress = 1f,
            projectId = 3,
            startDate = LocalDate.of(2026, 9, 2),
            endDate = LocalDate.of(2026, 9, 10)
        ),
        Task(
            id = 9,
            name = "Compose Layout",
            title = "Learn Row, Column and Box",
            progress = 0.80f,
            projectId = 3,
            startDate = LocalDate.of(2026, 9, 11),
            endDate = LocalDate.of(2026, 9, 20)
        ),
        Task(
            id = 10,
            name = "ViewModel",
            title = "Learn state management with ViewModel",
            progress = 0.45f,
            projectId = 3,
            startDate = LocalDate.of(2026, 9, 21),
            endDate = LocalDate.of(2026, 10, 5)
        ),

        // Daily Workout - projectId = 4
        Task(
            id = 11,
            name = "Workout Plan",
            title = "Create weekly workout schedule",
            progress = 0.90f,
            projectId = 4,
            startDate = LocalDate.of(2026, 9, 10),
            endDate = LocalDate.of(2026, 9, 12)
        ),
        Task(
            id = 12,
            name = "Morning Exercise",
            title = "Exercise for 30 minutes every morning",
            progress = 0.50f,
            projectId = 4,
            startDate = LocalDate.of(2026, 9, 13),
            endDate = LocalDate.of(2026, 12, 31)
        )
    )

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
    HomeScreen(name = "Nghiem Viet Duc Toan")
}

