package ies.sequeros.com.dam.pmdm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


interface Platform {
    val name: String
}
expect fun generateUUID(): String
expect fun getPlatform(): Platform
