package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.TaskStatus
import com.example.todoapp.model.Task
import com.example.todoapp.utils.calculateProjectProgress
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ProjectInfoCard(
    project: Project,
    category: Category?,
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    val progress = calculateProjectProgress(project.id, tasks)
    val totalTasks = tasks.size
    val completedCount = tasks.count { it.status == TaskStatus.COMPLETED }
    val inProgressCount = tasks.count { it.status == TaskStatus.IN_PROGRESS }
    val todoCount = tasks.count { it.status == TaskStatus.TODO }

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val startDateStr = project.startDate.format(dateFormatter)
    val endDateStr = project.endDate.format(dateFormatter)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Category Badge & Timeline
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (category != null) {
                    Row(
                        modifier = Modifier
                            .background(
                                color = category.backgroundColor,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.name,
                            tint = category.color,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = category.name,
                            color = category.color,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Project Timeline",
                        tint = Color(0xFF757575),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$startDateStr - $endDateStr",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575)
                    )
                }
            }

            // Project Title
            Text(
                text = project.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E2D)
            )

            // Project Description
            if (project.description.isNotBlank()) {
                Text(
                    text = project.description,
                    fontSize = 13.sp,
                    color = Color(0xFF6B7280),
                    lineHeight = 18.sp
                )
            }

            // Overall Progress
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tiến độ dự án",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24252A)
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5F33E1)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xFFEDE7FF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .height(10.dp)
                            .clip(CircleShape)
                            .background(color = Color(0xFF5F33E1))
                    )
                }
            }

            // Quick Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatItem(
                    label = "Tổng",
                    count = totalTasks,
                    bgColor = Color(0xFFF3F4F6),
                    textColor = Color(0xFF1E1E2D),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Chờ làm",
                    count = todoCount,
                    bgColor = Color(0xFFE7F3FF),
                    textColor = Color(0xFF0087FF),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Đang làm",
                    count = inProgressCount,
                    bgColor = Color(0xFFFFEFE9),
                    textColor = Color(0xFFFF7D53),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Xong",
                    count = completedCount,
                    bgColor = Color(0xFFEDE7FF),
                    textColor = Color(0xFF5F33E1),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    count: Int,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = bgColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = count.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectInfoCardPreview() {
    val project = Project(
        id = 1,
        name = "Dự án App Mobile",
        categoryId = 1,
        description = "Phát triển Todo App với giao diện Jetpack Compose hiện đại",
        startDate = LocalDate.now().minusDays(5),
        endDate = LocalDate.now().plusDays(25)
    )

    val category = Category(
        id = 1,
        name = "Công việc",
        color = Color(0xFFF478B8),
        backgroundColor = Color(0xFFFFE4F2),
        icon = Icons.Default.Work
    )

    Box(modifier = Modifier.padding(16.dp)) {
        ProjectInfoCard(
            project = project,
            category = category,
            tasks = emptyList()
        )
    }
}
