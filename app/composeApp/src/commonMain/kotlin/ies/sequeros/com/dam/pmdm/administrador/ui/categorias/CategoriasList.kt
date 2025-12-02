package ies.sequeros.com.dam.pmdm.administrador.ui.categorias

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier


//Hay que realizar el merge para poder realizar los casos de uso y más
@Composable
fun Categorias(
    categoriasViewModel: CategoriasViewModel,
    onClose: () -> Unit
) {
    //val items by categoriasViewModel.items.collectAsState()
    Text("Categorias")
    Column(modifier = Modifier.fillMaxSize()) {

    }
}