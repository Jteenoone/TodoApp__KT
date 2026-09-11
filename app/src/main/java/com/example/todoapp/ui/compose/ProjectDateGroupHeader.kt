package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ProjectDateGroupHeader(
    date: LocalDate,
    taskCount: Int,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val dayPrefix = when {
        date == today -> "Hôm nay"
        date == today.plusDays(1) -> "Ngày mai"
        date == today.minusDays(1) -> "Hôm qua"
        else -> formatDayOfWeek(date)
    }

    val formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(color = Color(0xFFEDE7FF), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    tint = Color(0xFF5F33E1),
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$dayPrefix, $formattedDate",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF24252A)
            )
        }

        Box(
            modifier = Modifier
                .background(color = Color(0xFFEDE7FF), shape = CircleShape)
                .padding(horizontal = 10.dp, vertical = 3.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$taskCount công việc",
                color = Color(0xFF5F33E1),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun formatDayOfWeek(date: LocalDate): String {
    return when (date.dayOfWeek) {
        DayOfWeek.MONDAY -> "Thứ Hai"
        DayOfWeek.TUESDAY -> "Thứ Ba"
        DayOfWeek.WEDNESDAY -> "Thứ Tư"
        DayOfWeek.THURSDAY -> "Thứ Năm"
        DayOfWeek.FRIDAY -> "Thứ Sáu"
        DayOfWeek.SATURDAY -> "Thứ Bảy"
        DayOfWeek.SUNDAY -> "Chủ Nhật"
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectDateGroupHeaderPreview() {
    ProjectDateGroupHeader(
        date = LocalDate.now(),
        taskCount = 3
    )
}
