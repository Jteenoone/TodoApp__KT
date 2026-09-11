package com.example.todoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.ui.compose.AreaListDateCard
import com.example.todoapp.ui.compose.TaskDetailCard
import com.example.todoapp.viewmodel.TodoViewModel
import java.time.LocalDate

@Composable
fun CalendarScreen(
    viewModel: TodoViewModel,
) {
    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Top Bar
        Row(
            modifier = Modifier.height(70.dp).fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(text = "Today's Tasks", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Box (modifier = Modifier.size(48.dp)){
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notify", modifier = Modifier.size(28.dp))
                }
                Box(modifier = Modifier.size(6.dp).offset(x = (-15).dp, y = 12.dp).align(Alignment.TopEnd).background(color = Color(0xFF5F33E1), shape = CircleShape))
            }
        }

        // Date Picker Area
        AreaListDateCard(
            selectedDate = selectedDate,
            onDateSelected = {date -> selectedDate = date},
            modifier = Modifier.height(110.dp).fillMaxWidth()
        )

        // Task List - Added weight(1f) to enable scrolling properly
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val dailyTasks = viewModel.getTaskByDate(selectedDate)
            items(dailyTasks) { item ->
                val project: Project? = viewModel.getProjectById(item.projectId)
                val category : Category? = viewModel.getCategoryById(project?.categoryId ?: 0)
                
                if(category != null && project != null) {
                    TaskDetailCard(
                        task = item,
                        category = category,
                        project = project
                    )
                }
            }
        }
    }
}
