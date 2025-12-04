package ies.sequeros.com.dam.pmdm.administrador.ui.productos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO

@Composable
fun Productos(
    productosViewModel: ProductoViewModel,
    onSelectItem: (ProductoDTO?) -> Unit,
    onClose: () -> Unit
) {

    val items by productosViewModel.items.collectAsState()
    var searchText by remember { mutableStateOf("") }

    // Filtrado por nombre del producto
    val filteredItems = items.filter {
        if (searchText.isNotBlank()) {
            it.name.contains(searchText, ignoreCase = true)
        } else {
            true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text("Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            OutlinedButton(
                onClick = {
                    productosViewModel.setSelectedProducto(null)
                    onSelectItem(null)
                },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 140.dp),
            modifier = Modifier.weight(1f).padding(8.dp)
        ) {
            items(filteredItems.size) { index ->
                val item = filteredItems[index]
                ProductoCard(
                    item = item,
                    onActivate = {
                        // Aquí podrías cambiar el estado del producto si es necesario
                    },
                    onDeactivate = {
                        // Aquí podrías cambiar el estado del producto si es necesario
                    },
                    onDelete = {
                        productosViewModel.delete(it)
                    }
                )
            }
        }
    }
}
