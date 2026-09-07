package com.example.todoapp.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: Int,
    val name: String,
    val color: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)