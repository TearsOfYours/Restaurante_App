package ies.sequeros.com.dam.pmdm.cliente.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginClienteViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginClienteState())
    val uiState: StateFlow<LoginClienteState> = _uiState.asStateFlow()

    // --- VALIDACIÓN ---
    private fun validateNombre(nombre: String): String? {
        if (nombre.isBlank()) return "El nombre es obligatorio"
        if (nombre.length < 2) return "El nombre es muy corto"
        return null
    }

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(
            nombre = v,
            nombreError = validateNombre(v)
        )
    }

    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        state.nombreError == null && state.nombre.isNotBlank()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.nombre)

        _uiState.value = s.copy(
            nombreError = nombreErr,
            submitted = true
        )

        return nombreErr == null
    }

    fun submit(
        onSuccess: (LoginClienteState) -> Unit,
        onFailure: ((LoginClienteState) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val ok = validateAll()
            if (ok) onSuccess(_uiState.value)
            else onFailure?.invoke(_uiState.value)
        }
    }

    fun clear() {
        _uiState.value = LoginClienteState()
    }


}