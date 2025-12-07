package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria.cards.categoria


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre

@Suppress("UnrememberedMutableState")
@Composable
fun ClienteCategoriaCard(
    item: CategoriaDTO,
    toProducts: (CategoriaDTO) -> Unit,
    onSelect: (String) -> Unit,
    nombreCliente: (String)
) {
    val imagePath =
        mutableStateOf(if (item.imagePath != null && item.imagePath.isNotEmpty()) item.imagePath else "")
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        onClick = {
            toProducts(item)
            onSelect(item.id!!)},
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    //.clip(CircleShape)
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(10.dp)
                    )
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                ImagenDesdePath(imagePath,
                    Res.drawable.hombre,
                    Modifier.fillMaxSize()
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)))
            }
            Text(item.name)

        }
        //Falta por implementar el código para que se pueda activar o desactivar

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}