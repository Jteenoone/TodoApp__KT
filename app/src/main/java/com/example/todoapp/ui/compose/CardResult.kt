package com.example.todoapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardResult(
    value: Float,
    onView: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF5F33E1)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your today's task",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = "almost done!",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Button(
                    onClick = onView,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF5F33E1)
                    )
                ) {
                    Text(
                        text = "View Task",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Box(
                modifier= Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgress(
                    progress = (value / 100f),
                    colorBackgroundProgress = Color(0xFF8A68ED),
                    colorProgress = Color.White,
                    colorText = Color.White,
                    modifier = Modifier.size(90.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Surface(
                modifier = Modifier.size(24.dp),
                color = Color(0xFF9F85ED),
                shape = RoundedCornerShape(4.dp)
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Xem thêm",
                        tint = Color.White,
                        modifier = Modifier
                            .size(14.dp)
                            .rotate(90f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardResultPreview() {
    CardResult(
        value = 82f,
        onView = {},
        modifier = Modifier.height(180.dp).padding(16.dp)
    )
}