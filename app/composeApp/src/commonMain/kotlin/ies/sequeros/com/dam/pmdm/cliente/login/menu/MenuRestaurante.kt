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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(32.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        Text("Buenos días, $nombreCliente")
                        OutlinedButton(
                            onClick = { onTerminar() },
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Carrito de compra",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        )
                    }
                }, colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp, 0.dp, 32.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onTerminar() },
                        content = {
                            Text("Terminar Pedido")
                        }
                    )

                    Button(
                        onClick = { onExit() },
                        content = {
                            Text("Cancelar Pedido")
                        }
                    )

                }
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Row(modifier = Modifier.fillMaxWidth()) {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(
                            minSize = 200.dp
                        )
                    ) {
                        items(items.size) { index ->
                            ClienteCategoriaCard(
                                item = items[index],
                                toProducts = {selected ->
                                    menuCartaViewModel.setSelectedCategoria(selected.id)
                                    onToProduct()
                                },
                                onSelect = { id ->
                                    menuCartaViewModel.setSelectedCategoria(id)
                                    getCatId(id)
                                },

                            )

                        }
                    }


                }
            }

        }

    )


}