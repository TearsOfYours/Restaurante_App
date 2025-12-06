package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.producto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre

@Suppress("UnrememberedMutableState")
@Composable
fun ProductoCard(
    item: ProductoDTO,
    onAgregar: (ProductoDTO) -> Unit,
    onClick: (ProductoDTO) -> Unit
) {
    val imagePath =
        mutableStateOf(if (item.imagePath != null && item.imagePath.isNotEmpty()) item.imagePath else "")
    Card(
        modifier = Modifier.padding(8.dp).size(90.dp),
        onClick = { onClick(item) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ImagenDesdePath(imagePath,Res.drawable.hombre)

            Text(item.name)
            Text("${item.precio} €")
            Button(onClick = { onAgregar(item) }) {
                Text("Añadir")
            }
        }
    }
}