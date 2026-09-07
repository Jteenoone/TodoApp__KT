package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.example.todoapp.model.CategorySummary
import com.example.todoapp.model.Task

@Composable
fun TaskGroupCard(
    categorySummary: CategorySummary,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color =categorySummary.category.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = categorySummary.category.color,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
            ) {
                Text(
                    text = categorySummary.category.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                Text(
                    text = "${categorySummary.taskCount} Tasks",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                CircularProgress(
                    progress = (categorySummary.progress / 100f),
                    colorBackgroundProgress = categorySummary.category.backgroundColor,
                    colorProgress = categorySummary.category.color,
                    colorText = Color.Black,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TaskGroupCardPreview() {
    val category =  Category(
        id = 1,
        name = "Office Project",
        color = Color(0xFFF478B8),
        backgroundColor = Color(0xFFFFE4F2),
        icon = Icons.Default.Work
    )

    val categorySummary = CategorySummary(
        category= category,
        taskCount = 23,
        progress = 70f
    )
    TaskGroupCard(
        categorySummary = categorySummary
    )
}