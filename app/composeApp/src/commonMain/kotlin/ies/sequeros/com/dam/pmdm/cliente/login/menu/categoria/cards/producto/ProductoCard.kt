package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.producto

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        modifier = Modifier
            .padding(8.dp)
            .width(140.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onClick(item) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                ImagenDesdePath(
                    imagePath,
                    Res.drawable.hombre,
                    Modifier.fillMaxSize()
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)))
            }

            // Nombre y precio
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "${item.precio} €",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}