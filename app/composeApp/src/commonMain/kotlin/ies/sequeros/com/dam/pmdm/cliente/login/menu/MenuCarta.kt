package ies.sequeros.com.dam.pmdm.cliente.login.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mysql.cj.xdevapi.Column
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCarta(
    menuCartaViewModel: MenuCartaViewModel,
    categoriaRepositorio: ICategoriaRepositorio,
    productoRepositorio: IProductoRepositorio,
    almacenDatos: AlmacenDatos,
    nombreCliente: String,
    onExit: () -> Unit,
    onTerminar: () -> Unit,
) {

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
                    Text(
                        text = "Este es el Contenido Principal de la Pantalla.",
                        modifier = Modifier
                    )



                }
            }

        }

    )


}