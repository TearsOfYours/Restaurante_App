package ies.sequeros.com.dam.pmdm.administrador.ui


import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel


import kotlinx.coroutines.flow.MutableStateFlow



data class ItemOption(val icon: ImageVector, val action:()->Unit, val name:String, val admin: Boolean)
class MainAdministradorViewModel(): ViewModel() {

    private val _options= MutableStateFlow<List<ItemOption>>(emptyList())

    fun setOptions(options:List<ItemOption>){

        _options.value = options.toList() // fuerza una nueva referencia

    }
    val filteredItems = _options/*combine(_options, appUser) { items, user ->
        val isAdmin = user?.isAdmin ?: false
       // if (isAdmin)
            items // muestra todo
        //else
        //   items.filter { !it.admin } // oculta admin-only
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )*/


}