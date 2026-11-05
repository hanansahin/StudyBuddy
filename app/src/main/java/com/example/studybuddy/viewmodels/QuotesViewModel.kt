package com.example.studybuddy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.studybuddy.data.QuotesRepository

class QuotesViewModel : ViewModel() {

    private val _quote = MutableStateFlow("Loading...")
    val quote: StateFlow<String> = _quote

    init {
        loadNewQuote()
    }

    fun loadNewQuote() {
        viewModelScope.launch {
            _quote.value = QuotesRepository.getRandomQuote()
        }
    }
}
