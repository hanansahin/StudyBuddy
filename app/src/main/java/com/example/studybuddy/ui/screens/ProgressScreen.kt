package com.example.studybuddy.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddy.viewmodels.TaskViewModel

@Composable
fun ProgressScreen(taskViewModel: TaskViewModel) {

    val tasks by taskViewModel.tasks.collectAsState()

    val total = tasks.size
    val completed = tasks.count { it.completed }

    val progress = if (total == 0) 0f else completed / total.toFloat()

    val animatedProgress by animateFloatAsState(targetValue = progress)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Your Progress",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(30.dp))

        // progress Ring
        Box(contentAlignment = Alignment.Center) {

            CircularProgressIndicator(
                progress = animatedProgress,
                strokeWidth = 12.dp,
                modifier = Modifier.size(180.dp)
            )

            Text(
                text = "${(animatedProgress * 100).toInt()}%",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(40.dp))

        // stats card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "Task Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(12.dp))

                Text("Total tasks: $total", fontSize = 18.sp)
                Text("Completed: $completed", fontSize = 18.sp)
            }
        }
    }
}
