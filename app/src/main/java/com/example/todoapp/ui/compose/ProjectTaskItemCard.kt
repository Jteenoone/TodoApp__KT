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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Pending
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
import com.example.todoapp.model.Task
import com.example.todoapp.model.TaskStatus
import com.example.todoapp.model.backgroundColor
import com.example.todoapp.model.color
import com.example.todoapp.model.label
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ProjectTaskItemCard(
    task: Task,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    val startTime = task.startDate.format(timeFormatter)
    val endTime = task.endDate.format(timeFormatter)

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dayOfWeek = formatDayOfWeek(task.startDate.toLocalDate())
    val dateText = "$dayOfWeek, ${task.startDate.format(dateFormatter)}"

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header: Status Icon, Task Name/Title, Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Circular status indicator icon
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(color = task.status.backgroundColor(), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (task.status == TaskStatus.COMPLETED) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = task.status.color(),
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Pending,
                            contentDescription = task.status.label(),
                            tint = task.status.color(),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = task.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E2D)
                    )
                    if (task.title.isNotBlank()) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = task.title,
                            fontSize = 13.sp,
                            color = Color(0xFF757575)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Status badge
                Box(
                    modifier = Modifier
                        .background(color = task.status.backgroundColor(), shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = task.status.label(),
                        color = task.status.color(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Date & Time Row (Thứ, ngày, tháng và giờ)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFF9F8FD), shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Thứ, ngày tháng
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        tint = Color(0xFF5F33E1),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = dateText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF424242)
                    )
                }

                // Giờ bắt đầu - Giờ kết thúc
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Time",
                        tint = Color(0xFFAB94FF),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$startTime - $endTime",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF5F33E1)
                    )
                }
            }

            // Task Progress indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xFFEEE9FF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(task.progress.coerceIn(0f, 1f))
                            .height(6.dp)
                            .clip(CircleShape)
                            .background(color = task.status.color())
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${(task.progress * 100).toInt()}%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = task.status.color()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectTaskItemCardPreview() {
    val sampleTask = Task(
        id = 1,
        name = "Thiết kế UI Flow",
        title = "Hoàn thành wireframe cho màn hình chi tiết dự án",
        progress = 0.65f,
        projectId = 1,
        startDate = LocalDateTime.of(2026, 9, 15, 9, 0),
        endDate = LocalDateTime.of(2026, 9, 15, 11, 30)
    )
    Box(modifier = Modifier.padding(16.dp)) {
        ProjectTaskItemCard(task = sampleTask)
    }
}
