package com.example.studybuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddy.viewmodels.QuotesViewModel

@Composable
fun MotivationScreen(
    quotesViewModel: QuotesViewModel = viewModel()
) {
    val quote by quotesViewModel.quote.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = quote,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = { quotesViewModel.loadNewQuote() },
            shape = MaterialTheme.shapes.large
        ) {
            Text("New Quote")
        }
    }
}
