package com.example.studybuddy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studybuddy.data.TaskRepository
import com.example.studybuddy.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = TaskRepository.loadTasks()
        }
    }

    fun addTask(title: String, desc: String = "") {
        viewModelScope.launch {
            TaskRepository.addTask(title, desc)
            loadTasks()
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch {
            TaskRepository.deleteTask(id)
            loadTasks()
        }
    }

    fun toggleCompleted(task: Task) {
        viewModelScope.launch {
            val updated = task.copy(completed = !task.completed)
            TaskRepository.updateTask(updated)
            loadTasks()
        }
    }
}
