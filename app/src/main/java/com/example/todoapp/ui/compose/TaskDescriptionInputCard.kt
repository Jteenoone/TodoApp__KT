package com.example.todoapp.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TaskDescriptionInputCard(
    description: String,
    onChange: (description: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier.height(160.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
        ) {
            Text(
                text = "Description",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Spacer(
                Modifier.height(8.dp)
            )
            BasicTextField(
                value = description,
                onValueChange = {onChange(it)},
                modifier= Modifier.fillMaxWidth(),
                singleLine = false,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF24252A)
                ),
                cursorBrush = SolidColor(Color(0xFF5F33E1)),
                decorationBox = { innerTextField ->
                    if(description.isEmpty()) {
                        Text(
                            text = "Enter description",
                            fontSize = 14.sp,
                            color = Color.LightGray
                        )
                    }

                    innerTextField()
                }
            )
        }
    }
}