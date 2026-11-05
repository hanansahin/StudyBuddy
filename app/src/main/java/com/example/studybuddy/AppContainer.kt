package com.example.studybuddy

import android.content.Context
import com.example.studybuddy.data.TaskRepository

object AppContainer {
    private var initialized = false

    fun init(context: Context) {
        if (initialized) return
        TaskRepository.init(context)
        Prefs.init(context)
        initialized = true
    }
}
