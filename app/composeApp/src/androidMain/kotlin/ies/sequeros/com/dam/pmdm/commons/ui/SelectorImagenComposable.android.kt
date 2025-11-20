package ies.sequeros.com.dam.pmdm.commons.ui

import androidx.compose.runtime.Composable

@Composable
actual fun SelectorImagenComposable(onImageSelected: (String) -> Unit) {
    SelectorImagen { uri ->
        onImageSelected(uri.toString())   // lo pasas como String
    }
}