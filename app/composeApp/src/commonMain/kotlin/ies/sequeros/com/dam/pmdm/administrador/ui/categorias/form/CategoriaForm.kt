package ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.CategoriasViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteFormViewModel
import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import ies.sequeros.com.dam.pmdm.commons.ui.SelectorImagenComposable
import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre

@Composable
fun CategoriaForm(
    categoriaViewModel: CategoriasViewModel,
    onClose: () -> Unit,
    onConfirm: (datos: CategoriaFormState) -> Unit = {},
    categoriaFormViewModel: CategoriaFormViewModel = viewModel {
        CategoriaFormViewModel(
            categoriaViewModel.selected.value)
    })
{
    val state by categoriaFormViewModel.uiState.collectAsState()
    val formValid by categoriaFormViewModel.isFormValid.collectAsState()
    val selected = categoriaViewModel.selected.collectAsState()
    val imagePath =
        remember { mutableStateOf(if (state.imagePath != null && state.imagePath.isNotEmpty()) state.imagePath else "") }


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = state.nombre,
            onValueChange = {categoriaFormViewModel.onNombreChange(it)},
            label= {Text("Nombre de categoria")},
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

        Text("Selecciona un avatar:", style = MaterialTheme.typography.titleSmall)

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
        val scope = rememberCoroutineScope()

        SelectorImagenComposable({ it: String ->
            categoriaFormViewModel.onImagenPathChange(it)//  dependienteViewModel.almacenDatos.copy(it, "prueba","/dependientes_imgs/")
            imagePath.value = it
        })

        ImagenDesdePath(imagePath, Res.drawable.hombre, Modifier.fillMaxSize().weight(1f))
        state.imagePathError?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                categoriaFormViewModel.submit(
                    onSuccess = {
                        onConfirm(categoriaFormViewModel.uiState.value)
                    },
                    onFailure = {}
                )
            },
            //enabled = formValid
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
        }



    }
}