package com.example.studybuddy.data

import android.content.Context
import com.example.studybuddy.models.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

object TaskRepository {
    private const val FILE_NAME = "tasks.json"
    private lateinit var context: Context
    private val gson = Gson()
    private val taskType = object : TypeToken<List<Task>>() {}.type

    fun init(ctx: Context) {
        context = ctx.applicationContext
    }

    suspend fun loadTasks(): List<Task> = withContext(Dispatchers.IO) {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return@withContext emptyList()
        val json = file.readText()
        gson.fromJson(json, taskType) ?: emptyList()
    }

    suspend fun saveTasks(tasks: List<Task>) = withContext(Dispatchers.IO) {
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(gson.toJson(tasks))
    }

    suspend fun addTask(title: String, desc: String = ""): Task {
        val tasks = loadTasks().toMutableList()
        val t = Task(UUID.randomUUID().toString(), title, desc, false)
        tasks.add(0, t)
        saveTasks(tasks)
        return t
    }

    suspend fun updateTask(task: Task) {
        val tasks = loadTasks().toMutableList()
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
            saveTasks(tasks)
        }
    }

    suspend fun deleteTask(id: String) {
        val tasks = loadTasks().filterNot { it.id == id }
        saveTasks(tasks)
    }
}
