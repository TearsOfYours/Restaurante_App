package ies.sequeros.com.dam.pmdm.cliente.login.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.categoria.ClienteCategoriaCard
import ies.sequeros.com.dam.pmdm.cliente.login.menu.scaffold.PantallaBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuRestaurante(
    menuCartaViewModel: MenuCartaViewModel,
    nombreCliente: String,
    onExit: () -> Unit,
    onTerminar: () -> Unit,
    onToProduct: () -> Unit,
    getCatId: (String) -> Unit
) {
    val items by menuCartaViewModel.items.collectAsState()

    PantallaBase(
        titulo = { Text("Buenos días, $nombreCliente") },
        onTerminar = { onTerminar() },
        onSalir ={ onExit() }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(200.dp)
            ) {
                items(items.size) { index ->

                    ClienteCategoriaCard(
                        item = items[index],
                        toProducts = { selected ->
                            menuCartaViewModel.setSelectedCategoria(selected.id)
                            onToProduct()
                        },
                        onSelect = { id ->
                            menuCartaViewModel.setSelectedCategoria(id)
                            getCatId(id)
                        }
                    )
                }
            }
        }
    }
}


