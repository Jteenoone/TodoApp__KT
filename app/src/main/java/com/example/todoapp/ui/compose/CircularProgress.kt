package com.example.todoapp.ui.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgress(
    progress: Float,
    colorProgress: Color,
    colorBackgroundProgress: Color,
    colorText: Color,
    modifier: Modifier = Modifier
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val strokeWidth = 10.dp.toPx()

            drawArc(
                color = colorBackgroundProgress,
                startAngle = -90f,
                sweepAngle = -360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                color = colorProgress,
                startAngle = -90f,
                sweepAngle = -(360f * safeProgress),
                useCenter = false,
               style = Stroke(
                   width = strokeWidth,
                   cap = StrokeCap.Round
               )
            )
        }

        Text(
            text = "${(safeProgress * 100).toInt()}%",
            color = colorText,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun CircularProgressPreview() {
    CircularProgress(
        progress = 0.82f,
        colorBackgroundProgress = Color(0xFF8A68ED),
        colorProgress = Color.White,
        colorText = Color.White,
        modifier = Modifier.size(70.dp)
    )
}