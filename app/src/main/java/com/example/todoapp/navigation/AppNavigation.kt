package com.example.todoapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.data.RoomTodoRepository
import com.example.todoapp.data.local.AppDatabase
import com.example.todoapp.ui.screen.AddProjectScreen
import com.example.todoapp.ui.screen.AddTaskScreen
import com.example.todoapp.ui.screen.MainLayout
import com.example.todoapp.ui.screen.NotFoundProjectScreen
import com.example.todoapp.ui.screen.ProjectDetailScreen
import com.example.todoapp.ui.screen.StartScreen
import com.example.todoapp.viewmodel.TodoViewModel
import com.example.todoapp.viewmodel.TodoViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val factory = remember(context) {
        val database = AppDatabase.getInstance(context)
        TodoViewModelFactory(RoomTodoRepository(database))
    }
    val viewModel: TodoViewModel = viewModel(factory = factory)
    NavHost(
        navController = navController,
        startDestination = Routes.START
    ) {
        composable(Routes.START) {
            StartScreen(
                onStart = { navController.navigate(Routes.MAIN_LAYOUT) }
            )
        }

        composable(Routes.MAIN_LAYOUT) {
            MainLayout(
                viewModel=viewModel,
                onAddItem = {navController.navigate(Routes.ADD_PROJECT)},
                onClickCardProject = { projectId ->
                    navController.navigate(Routes.projectDetail(projectId))
                }
            )
        }

        composable(Routes.ADD_PROJECT) {
            AddProjectScreen(
                viewModel = viewModel,
                onBack= {navController.popBackStack()},
                onDone = { projectId ->
                    navController.navigate(Routes.projectDetail(projectId)) {
                        popUpTo(Routes.ADD_PROJECT) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            Routes.PROJECT_DETAIL,
            arguments= listOf(
                navArgument("projectId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry->
            val projectId = backStackEntry.arguments?.getInt("projectId") ?:return@composable NotFoundProjectScreen { navController.popBackStack() }

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            if(viewModel.uiState.collectAsState().value.isLoading) return@composable NotFoundProjectScreen { navController.popBackStack() }
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.projects.none {it.id == projectId} -> {
                    NotFoundProjectScreen { navController.popBackStack() }
                }
                else -> {
                    ProjectDetailScreen(
                        viewModel = viewModel,
                        projectId = projectId,
                        onAddTask = { navController.navigate(Routes.addTask(projectId)) },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
        composable(
            Routes.ADD_TASK,
            arguments =  listOf(
                navArgument("projectId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getInt("projectId") ?: return@composable

            AddTaskScreen(
                viewModel=viewModel,
                projectId = projectId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
