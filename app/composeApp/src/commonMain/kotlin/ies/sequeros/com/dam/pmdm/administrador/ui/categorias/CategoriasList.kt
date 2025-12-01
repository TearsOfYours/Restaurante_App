package ies.sequeros.com.dam.pmdm.administrador.ui.categorias

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Categorias(
    categoriasViewModel: CategoriasViewModel,
    onClose: () -> Unit
) {
    Text("Categorias")
    Column(modifier = Modifier.fillMaxSize()) {

    }
}