package ies.sequeros.com.dam.pmdm.administrador.ui.productos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form.CategoriaFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoFormViewModel(private val item: ProductoDTO?) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProductoFormState(
            nombre = item?.name ?: "",
            imagePath = item?.imagePath ?: "",
            precio = item?.precio ?: 0.0,
            idCategoria = item?.idCategoria ?: "",
            id = item?.id ?: ""
        )
    )

    val uiState: StateFlow<ProductoFormState> = _uiState.asStateFlow()

    val isFormValid: StateFlow<Boolean> = uiState
        .map { state ->
            state.nombreError == null &&
                    state.precioError == null &&
                    state.idCategoriaError == null &&
                    state.imagePathError == null &&

                    state.nombre.isNotBlank() &&
                    state.precio > 0.0 &&
                    state.idCategoria.isNotBlank() &&
                    state.imagePath.isNotBlank()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )


    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(nombre = v, nombreError = validateNombre(v))
    }

    fun onPrecioChange(v: String) {
        val precio = v.toDoubleOrNull() ?: -1.0
        _uiState.value = _uiState.value.copy(
            precio = if (precio >= 0) precio else 0.0,
            precioError = if (precio >= 0) validatePrecio(precio) else "Precio inválido"
        )
    }

    fun onIdCategoriaChange(v: String) {
        _uiState.value = _uiState.value.copy(idCategoria = v, idCategoriaError = validateIdCategoria(v))
    }

    fun onImagenPathChange(v: String) {
        _uiState.value = _uiState.value.copy(imagePath = v, imagePathError = validateImagePath(v))
    }


    private fun validateNombre(nombre: String): String? {
        if (nombre.length < 4) return "El nombre es muy corto"
        return null
    }

    private fun validateImagePath(path: String): String? {
        if (path.isBlank()) return  "No hay imagen"
        return null
    }


    private fun validatePrecio(precio: Double): String? {
        if (precio <= 0.0) return "El precio debe ser mayor que 0"
        return null
    }

    private fun validateIdCategoria(idCat: String): String? {
        if (idCat.isBlank()) return "Selecciona una categoría"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.nombre)
        val precioErr = validatePrecio(s.precio)
        val idCatErr = validateIdCategoria(s.idCategoria)
        val imgErr = validateImagePath(s.imagePath)

        _uiState.value = s.copy(
            nombreError = nombreErr,
            precioError = precioErr,
            idCategoriaError = idCatErr,
            imagePathError = imgErr,
            submitted = true
        )
        return listOf(nombreErr, precioErr, idCatErr, imgErr).all { it == null }
    }

    fun submit(onSuccess: (ProductoFormState) -> Unit, onFailure: ((ProductoFormState) -> Unit)? = null) {
        viewModelScope.launch {
            val ok = validateAll()
            if (ok) onSuccess(_uiState.value) else onFailure?.invoke(_uiState.value)
        }
    }

}
