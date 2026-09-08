package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
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

@Composable
fun TaskGroupDropdown(
    categories: List<Category>,
    selectedGroup: Category,
    onClick: (category: Category) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
                .height(80.dp)
                .clickable {expanded = true},
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(40.dp)
                        .background(color = selectedGroup.backgroundColor, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = selectedGroup.icon,
                        contentDescription = selectedGroup.name,
                        tint = selectedGroup.color,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier= Modifier.width(20.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Task Group",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Text(
                        text = selectedGroup.name,
                        color = Color(0xFF24252A),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Chọn Task Group",
                    tint = Color(0xFF24252A),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = {
                        Text(category.name)
                    },
                    onClick={
                        onClick(category)
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.name,
                            tint = category.color
                        )
                    }
                )
            }
        }
    }
}