package com.example.todoapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.todoapp.model.Task
import com.example.todoapp.ui.compose.BottomBarItem
import com.example.todoapp.ui.compose.CardResult
import com.example.todoapp.ui.compose.TaskGroupCard
import com.example.todoapp.ui.compose.TaskProgressCard
import com.example.todoapp.utils.CategorySummaries
import java.nio.channels.Selector

@Composable
fun HomeScreen(
    name: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar(name = name)
        HomeContent(modifier = Modifier)
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
    val tasks = listOf(
        // Office Project
        Task(
            id = 1,
            title = "Design shopping application",
            categoryId = 1,
            progress = 0.85f
        ),
        Task(
            id = 2,
            title = "Create project database",
            categoryId = 1,
            progress = 0.72f
        ),
        Task(
            id = 3,
            title = "Prepare weekly report",
            categoryId = 1,
            progress = 1f
        ),
        Task(
            id = 4,
            title = "Team meeting preparation",
            categoryId = 1,
            progress = 0.45f
        ),

        // Personal Project
        Task(
            id = 5,
            title = "Redesign personal portfolio",
            categoryId = 2,
            progress = 1f
        ),
        Task(
            id = 6,
            title = "Update GitHub profile",
            categoryId = 2,
            progress = 0.65f
        ),
        Task(
            id = 7,
            title = "Build weather application",
            categoryId = 2,
            progress = 0.35f
        ),

        // Daily Study
        Task(
            id = 8,
            title = "Learn Jetpack Compose",
            categoryId = 3,
            progress = 0.8f
        ),
        Task(
            id = 9,
            title = "Practice Kotlin exercises",
            categoryId = 3,
            progress = 0.5f
        ),
        Task(
            id = 10,
            title = "Read Android documentation",
            categoryId = 3,
            progress = 1f
        ),
        Task(
            id = 11,
            title = "Review database lesson",
            categoryId = 3,
            progress = 0.25f
        ),

        // Health & Fitness
        Task(
            id = 12,
            title = "Morning workout",
            categoryId = 4,
            progress = 1f
        ),
        Task(
            id = 13,
            title = "Run five kilometers",
            categoryId = 4,
            progress = 0.6f
        ),
        Task(
            id = 14,
            title = "Drink enough water",
            categoryId = 4,
            progress = 0.75f
        ),

        // Shopping
        Task(
            id = 15,
            title = "Buy groceries",
            categoryId = 5,
            progress = 0.4f
        ),
        Task(
            id = 16,
            title = "Order a new keyboard",
            categoryId = 5,
            progress = 1f
        ),

        // Home Tasks
        Task(
            id = 17,
            title = "Clean the bedroom",
            categoryId = 6,
            progress = 0.9f
        ),
        Task(
            id = 18,
            title = "Wash clothes",
            categoryId = 6,
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

