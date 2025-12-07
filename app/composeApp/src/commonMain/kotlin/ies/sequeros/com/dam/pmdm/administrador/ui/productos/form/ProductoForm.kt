package ies.sequeros.com.dam.pmdm.administrador.ui.Producto.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.CategoriasViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.ProductoViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.form.CategoriasComboBox
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.form.ProductoFormState
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.form.ProductoFormViewModel
import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import ies.sequeros.com.dam.pmdm.commons.ui.SelectorImagenComposable
import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre

@Composable
fun ProductoForm(
    productoViewModel: ProductoViewModel,
    categoriasViewModel: CategoriasViewModel,
    onClose: () -> Unit,
    onConfirm: (datos: ProductoFormState) -> Unit = {},
    productoFormViewModel: ProductoFormViewModel = viewModel {
        ProductoFormViewModel(productoViewModel.selected.value)
    }
) {
    val state by productoFormViewModel.uiState.collectAsState()
    val formValid by productoFormViewModel.isFormValid.collectAsState()
    val categorias by categoriasViewModel.items.collectAsState()

    val imagePath =
        remember { mutableStateOf(if (state.imagePath != null && state.imagePath.isNotEmpty()) state.imagePath else "") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // ------------------ Nombre ---------------------
        OutlinedTextField(
            value = state.nombre,
            onValueChange = { productoFormViewModel.onNombreChange(it) },
            label = { Text("Nombre del producto") },
            isError = state.nombreError != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.nombreError?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // ------------------ Precio ---------------------
        OutlinedTextField(
            value = if (state.precio == 0.0) "" else state.precio.toString(),
            onValueChange = { value ->
                productoFormViewModel.onPrecioChange(value)
            },
            label = { Text("Precio") },
            isError = state.precioError != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.precioError?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // ------------------ Categoría ---------------------
        Text("Categoría:", style = MaterialTheme.typography.titleSmall)

        CategoriasComboBox(
            categorias = categorias,
            current = categorias.find { it.id == state.idCategoria },
            onSelect = { categoria ->
                productoFormViewModel.onIdCategoriaChange(categoria.id)
            }
        )
        state.idCategoriaError?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // ------------------ Imagen ---------------------
        Text("Selecciona una imagen:", style = MaterialTheme.typography.titleSmall)

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

        SelectorImagenComposable { it: String ->
            productoFormViewModel.onImagenPathChange(it)
            imagePath.value = it
        }

        ImagenDesdePath(imagePath, Res.drawable.hombre, Modifier.fillMaxSize().weight(1f))

        state.imagePathError?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // ------------------ Botón guardar ---------------------
        Button(
            onClick = {
                productoFormViewModel.submit(
                    onSuccess = { onConfirm(state) },
                    onFailure = {},

                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Text(" Guardar")
        }
    }
}
