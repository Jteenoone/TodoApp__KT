package com.example.todoapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.compose.CommonTopBar

@Composable
fun NotFoundProjectScreen(
    onBack: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonTopBar(
                title = "Project not found",
                onBack = onBack,
                onNotificationClick = {}
            )
        }
    ) {
        innerPadding-> Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
        Text(
            text = "Not Found Project",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun NotFoundProjectScreenPreview() {
    NotFoundProjectScreen(
        onBack = {}
    )
}