package ies.sequeros.com.dam.pmdm

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow

private val LightColors = lightColorScheme(
    primary = Color(0xFF00A461), //Color(0xFF0061A4),
    onPrimary = Color.White,
    secondary = Color(0xFF006D3E),
    onSecondary = Color.White,
    background = Color(0xFFFDFDFD),
    onBackground = Color(0xFF1C1B1F)

)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF42D192),//0xFF9CCAFF),
    onPrimary = Color(0xFF003258),
    secondary = Color(0xFF8CDDA9),
    onSecondary = Color(0xFF00391F),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE5E1E6),
)

@Composable
fun AppTheme(
    darkTheme: State<Boolean>,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme.value) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,

        content = content
    )
}