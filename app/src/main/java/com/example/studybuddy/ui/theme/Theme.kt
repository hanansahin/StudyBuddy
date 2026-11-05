package com.example.studybuddy.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.studybuddy.R

// Colors
private val LightColors = lightColorScheme(
    primary = Color(0xFF5A68FF),
    onPrimary = Color.White,
    secondary = Color(0xFFFFE28A),
    onSecondary = Color(0xFF1A1A1A),
    background = Color(0xFFF9FAFE),
    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8893FF),
    onPrimary = Color.Black,
    secondary = Color(0xFFFFEA9A),
    background = Color(0xFF0F111A),
    surface = Color(0xFF1A1D27),
    onSurface = Color.White,
)

// Fonts
private val Poppins = FontFamily(
    Font(R.font.poppins_semibold)
)

private val Inter = FontFamily(
    Font(R.font.inter_regular)
)

// Typography
private val StudyTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontSize = 13.sp
    )
)

@Composable
fun StudyBuddyTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = StudyTypography,
        shapes = Shapes(
            small = androidx.compose.foundation.shape.RoundedCornerShape(12),
            medium = androidx.compose.foundation.shape.RoundedCornerShape(16),
            large = androidx.compose.foundation.shape.RoundedCornerShape(24)
        ),
        content = content
    )
}
