package com.example.todoapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sin

@Composable
fun TaskNameInputCard(
    taskName: String,
    onChange: (name: String)-> Unit,
    modifier: Modifier = Modifier,
) {
    Surface (
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
        ) {
            Text(
                text = "Project name",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Spacer(
                Modifier.height(8.dp)
            )
            BasicTextField(
                value = taskName,
                onValueChange = {onChange(it)},
                modifier= Modifier.fillMaxWidth().weight(1f),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF24252A)
                ),
                cursorBrush = SolidColor(Color(0xFF5F33E1)),
                decorationBox = { innerTextField ->
                    if(taskName.isEmpty()) {
                        Text(
                            text = "Enter task name",
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    }

                    innerTextField()
                }
            )
        }
    }
}