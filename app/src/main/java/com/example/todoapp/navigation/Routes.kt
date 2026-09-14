package com.example.todoapp.navigation

object Routes {

    const val START = "start"
    const val MAIN_LAYOUT = "main_layout"
    const val ADD_PROJECT = "add_project"
    const val ADD_TASK = "add_task/{projectId}"
    const val PROJECT_DETAIL = "project_detail/{projectId}"

    fun addTask(projectId: Int): String = "add_task/ $projectId"
    fun projectDetail(projectId: Int): String = "project_detail/ $projectId"
}