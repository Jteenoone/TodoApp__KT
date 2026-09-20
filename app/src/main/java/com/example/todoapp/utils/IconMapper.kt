package com.example.todoapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

fun getCategoryIcon(iconName: String): ImageVector {
    return when(iconName) {
        "word", "work" -> Icons.Default.Work
        "person" -> Icons.Default.Person
        "home" -> Icons.Default.Home
        "fitness" -> Icons.Default.FitnessCenter
        "study", "school" -> Icons.Default.MenuBook
        "shopping" -> Icons.Default.ShoppingCart
        else -> Icons.Default.Category
    }
}
