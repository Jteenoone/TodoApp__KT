package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import java.time.LocalDate

@Composable
fun TaskDetailCard(
    task: Task,
    category: Category,
    project: Project,
    modifier: Modifier = Modifier
) {
    val color = when(task.status) {
        "To Do" -> Color(0xFF0087FF)
        "In Progress" -> Color(0xFFFF7D53)
        "Done" -> Color(0xFF5F33E1)
        else -> Color.LightGray
    }
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                   text = task.title,
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Text(
                    text = project.name,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )

                Row() {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Time",
                        tint = Color(0xFFAB94FF),
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = "10:00 AM",
                        color = Color(0xFFAB94FF),
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier= Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.End,
               verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(color = category.backgroundColor, shape = RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.name,
                        modifier = Modifier.size(22.dp),
                        tint = category.color
                    )
                }
                Box(
                    modifier = Modifier
                        .background(color = color.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = task.status,
                        color = color,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)

@Composable
fun TaskDetailCardPreview() {
        val task = Task(
        id = 1,
        name = "Market Research",
        title = "Research shopping applications",
        progress = 0.85f,
        projectId = 1,
        startDate = LocalDate.of(2026, 9, 1),
        endDate = LocalDate.of(2026, 9, 5)
    )
    val category =  Category(
        id = 1,
        name = "Office Project",
        color = Color(0xFFF478B8),
        backgroundColor = Color(0xFFFFE4F2),
        icon = Icons.Default.Work
    )

    val project = Project(
        id = 1,
        name = "Shopping Application",
        categoryId = 1,
        description = "Design and develop a mobile shopping application",
        startDate = LocalDate.of(2026, 9, 1),
        endDate = LocalDate.of(2026, 10, 15)
    )

    TaskDetailCard(
        task=task,
        category=category,
        project = project
    )
}