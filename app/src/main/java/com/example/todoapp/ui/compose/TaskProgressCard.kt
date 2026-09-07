package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.model.Category
import com.example.todoapp.model.Task
import com.example.todoapp.model.TaskCardColors


@Composable
fun TaskProgressCard(
    task: Task,
    category: Category,
    modifier: Modifier = Modifier
) {
    val taskCardColorList = listOf(
        TaskCardColors(
            cardColor = Color(0xFFE7F3FF),
            progressColor = Color(0xFF0C8CE9)
        ),
        TaskCardColors(
            cardColor = Color(0xFFFFE9E2),
            progressColor = Color(0xFFFF7557)
        ),
        TaskCardColors(
            cardColor = Color(0xFFEDE5FF),
            progressColor = Color(0xFF8758F1)
        ),
        TaskCardColors(
            cardColor = Color(0xFFFFF4D8),
            progressColor = Color(0xFFFFBE0B)
        ),
        TaskCardColors(
            cardColor = Color(0xFFE7F8EB),
            progressColor = Color(0xFF55B96B)
        )
    )

    val taskCardColors = taskCardColorList[
            task.id.mod(taskCardColorList.size)
    ]
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = taskCardColors.cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier= Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = category.name,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Text(
                        text = task.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier= Modifier.height(7.dp))
                Box(
                    modifier = Modifier.size(30.dp).background(color = category.backgroundColor, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.name,
                        tint = category.color,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth().height(12.dp).clip(CircleShape).background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                        .fillMaxWidth(task.progress.coerceIn(0f, 1f))
                        .clip(CircleShape)
                        .background(color = taskCardColors.progressColor)
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TaskProgressCardPreview() {
    val task = Task(
        id = 1,
        categoryId = 1,
        title = "Grocery shopping app design",
        progress = 0.72f
    )
    val category =  Category(
        id = 1,
        name = "Office Project",
        color = Color(0xFFF478B8),
        backgroundColor = Color(0xFFFFE4F2),
        icon = Icons.Default.Work
    )

    TaskProgressCard(
        task = task,
        category = category,
        modifier = Modifier.height(115.dp).width(260.dp)
    )
}