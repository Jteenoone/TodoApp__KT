package com.example.todoapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

@Composable
fun StartScreen(
    onStart: ()-> Unit
) {
    Scaffold(

    ) {
        innerPadding -> StartContent(
        onStart=onStart,
        modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun StartContent(
    onStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.image_start_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Task Management &",
            maxLines = 1,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = "To-Do List",
            maxLines = 1,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text =  "This productive tool is designed to help",
            maxLines = 1,
            color = Color.Gray,
            fontSize = 17.sp
        )
        Text(
            text =  "you better manage your task",
            maxLines = 1,
            color = Color.Gray,
            fontSize = 17.sp
        )
        Text(
            text =  "project-wise conveniently!",
            maxLines = 1,
            color = Color.Gray,
            fontSize = 17.sp
        )
        Spacer(modifier= Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5F33E1),
                contentColor =  Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(bottom = 50.dp).padding(horizontal = 24.dp).fillMaxWidth(),
            onClick = onStart
        ) {
            Box(
                modifier= Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Let's Start",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "Bắt đầu",
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .size(30.dp),
                    tint = Color.White
                )
            }
        }
    }
}