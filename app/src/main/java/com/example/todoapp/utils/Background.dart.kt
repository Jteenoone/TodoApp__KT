package com.example.todoapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Background(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFBFF))
            .clipToBounds()
    ) {
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = (-100).dp, y = (-80).dp)
                .background(
                    color = Color(0xFFBDA7FF).copy(alpha = 0.15f),
                    shape = CircleShape
                )
                .blur(90.dp)
        )

        Box(
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 110.dp)
                .background(
                    color = Color(0xFFFFE7A7).copy(alpha = 0.2f),
                    shape = CircleShape
                )
                .blur(100.dp)
        )

        Box(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.BottomStart)
                .offset(x= (-100).dp, y= 100.dp)
                .background(
                    color = Color(0xFFA9E7FF).copy(alpha = 0.2f),
                    shape = CircleShape
                )
                .blur(110.dp)
        )
        content()
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BackgroundPreview() {
    Background(content = {})
}