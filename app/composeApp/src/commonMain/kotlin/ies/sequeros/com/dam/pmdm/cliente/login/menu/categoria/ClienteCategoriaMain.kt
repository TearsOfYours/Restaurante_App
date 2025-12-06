package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.producto.ProductoCard

//Aquí es donde se verán todos los productos de una categoría

@Composable
fun ClienteCategoriaMain(
    clienteCategoriaMainViewModel: ClienteCategoriaMainViewModel,
) {
    val state by clienteCategoriaMainViewModel.uiState.collectAsState()

    Column() {
        Text("Categoría: ")

        LazyColumn {
            items(state.productos) { producto ->
                ProductoCard(producto, {}, {})
            }
        }
    }

}