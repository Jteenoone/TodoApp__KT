package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.ui.screen.AddProjectScreen
import com.example.todoapp.ui.screen.AddTaskScreen
import com.example.todoapp.ui.screen.MainLayout
import com.example.todoapp.ui.screen.ProjectDetailScreen
import com.example.todoapp.ui.screen.StartScreen
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: TodoViewModel = viewModel()
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
                onClickCardProject = {projectId -> navController.navigate("project_detail/$projectId")}
            )
        }

        composable(Routes.ADD_PROJECT) {
            AddProjectScreen(
                viewModel = viewModel,
                onBack= {navController.popBackStack()}
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
            val projectId = backStackEntry.arguments?.getInt("projectId") ?: return@composable

            ProjectDetailScreen(
                viewModel = viewModel,
                projectId = projectId,
                onAddTask = {navController.navigate("add_task/$projectId")},
                onBack = {navController.popBackStack()}
            )
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
                onBack = {navController.popBackStack()}
            )
        }
    }
}