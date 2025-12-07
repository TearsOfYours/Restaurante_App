package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.producto.ProductoCard

//Aquí es donde se verán todos los productos de una categoría

@Composable
fun ClienteCategoriaMain(
    clienteCategoriaMainViewModel: ClienteCategoriaMainViewModel,
    idCat: (String)
) {
    val state by clienteCategoriaMainViewModel.uiState.collectAsState()

    LaunchedEffect(idCat) {
        clienteCategoriaMainViewModel.filtrarPorCategoria(idCat)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // ---------- COLUMNA IZQUIERDA (Listado de Cards) ----------
        Box(
            modifier = Modifier
                .weight(0.65f)    // Ocupa el 65% del ancho
                .fillMaxHeight()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.productosFiltrados) { producto ->
                    ProductoCard(
                        item = producto,
                        onClick = { /* seleccionar */ },
                        onAgregar = { /* agregar */ }
                    )
                }
            }
        }

        // ---------- SEPARADOR VERTICAL ----------
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color.LightGray)
        )

        // ---------- COLUMNA DERECHA (Detalle) ----------
        Box(
            modifier = Modifier
                .weight(0.35f)    // Ocupa el 35% del ancho
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text("Detalle del producto", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // Aquí pondrás los datos cuando selecciones un producto
                Text("Selecciona un producto para ver más información.")
            }
        }
    }

}