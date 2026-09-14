package com.example.todoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.compose.BottomBarItem
import com.example.todoapp.utils.Background
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun MainLayout(
    viewModel: TodoViewModel,
    onClickCardProject: (Int) -> Unit,
    onAddItem: () -> Unit
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }


        Scaffold(
            containerColor = Color.Transparent,
            // Khử insets để tránh TopBar bị tụt xuống 2 lần
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                MainBottom(
                    selectedIndex = selectedIndex,
                    onAddItem = onAddItem,
                    onClickItem = { index ->
                        selectedIndex = index
                    }
                )
            }
        ) { innerPadding ->
            Background(
                content = {
                    MainContent(
                        selectedIndex = selectedIndex,
                        viewModel = viewModel,
                        onClickCardProject = onClickCardProject,
                        // Truyền padding xuống thay vì dùng Modifier.padding
                        bottomPadding = innerPadding.calculateBottomPadding()
                    )
                }
            )
        }
}

@Composable
fun MainContent(
    selectedIndex: Int,
    viewModel: TodoViewModel,
    bottomPadding: Dp,
    onClickCardProject: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (selectedIndex) {
            // Đảm bảo HomeScreen cũng nhận bottomPadding
            0 -> HomeScreen(name = "Nghiem Toan", viewModel = viewModel, onClickCardProject = onClickCardProject ,bottomPadding = bottomPadding)
            1 -> CalendarScreen(viewModel = viewModel, bottomPadding = bottomPadding)
        }
    }
}

@Composable
fun MainBottom(
    selectedIndex: Int,
    onClickItem: (Int) -> Unit = {},
    onAddItem: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp
            ),
            color = Color(0xFFEDE7FF)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(
                    icon = Icons.Default.Home,
                    selected = selectedIndex == 0,
                    onClick = { onClickItem(0) },
                )
                BottomBarItem(
                    icon = Icons.Default.CalendarMonth,
                    selected = selectedIndex == 1,
                    onClick = { onClickItem(1) },
                )
                Spacer(Modifier.size(64.dp))
                BottomBarItem(
                    icon = Icons.AutoMirrored.Filled.Article,
                    selected = selectedIndex == 2,
                    onClick = { onClickItem(2) },
                )
                BottomBarItem(
                    icon = Icons.Default.Group,
                    selected = selectedIndex == 3,
                    onClick = { onClickItem(3) },
                )
            }
        }
        Box(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
                .background(color = Color.White, shape = CircleShape)
        )

        FloatingActionButton(
            onClick = onAddItem,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-6).dp),
            shape = CircleShape,
            containerColor = Color(0xFF5F33E1),
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 8.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Thêm Task",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
