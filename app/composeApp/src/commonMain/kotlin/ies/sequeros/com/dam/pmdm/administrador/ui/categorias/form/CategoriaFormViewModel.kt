package ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriaFormViewModel(private val item: CategoriaDTO?, ): ViewModel() {

    private val _uiState = MutableStateFlow(CategoriaFormState(
        nombre = item?.name ?: "",
        imagePath = item?.imagePath ?: ""
        ),
    )


    val uiState: StateFlow<CategoriaFormState> = _uiState.asStateFlow()

    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if (item == null) {
            state.nombreError == null &&
            state.imagePathError == null &&
            !state.nombre.isBlank()
            !state.imagePath.isBlank()
        } else {
            state.nombreError == null &&
            state.imagePathError == null &&
            !state.imagePath.isBlank()
            !state.nombre.isBlank()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(nombre = v, nombreError = validateNombre(v))
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

    private fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreError = validateNombre(s.nombre)
        val newState = s.copy(
            nombreError = nombreError,
            submitted = true

        )
        _uiState.value = newState
        return  listOf(nombreError).all { it == null }
    }

    fun submit(
        onSuccess: (CategoriaFormState) -> Unit,
        onFailure: ((CategoriaFormState) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val ok = validateAll()
            if (ok){
                onSuccess(_uiState.value)
            } else {
                onFailure?.invoke(_uiState.value)
            }
        }
    }

}