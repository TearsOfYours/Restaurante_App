package ies.sequeros.com.dam.pmdm.cliente.login.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar.ActualizarCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar.ActualizarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.ListarCategoriasUseCase
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form.CategoriaFormState
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuCartaViewModel(
    private val productoRepositorio: IProductoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    private val pedidoRepositorio: IPedidoRepositorio,
    private val almacenDatos: AlmacenDatos
) : ViewModel() {

    private val listarCategoriasUseCase: ListarCategoriasUseCase

    private val actualizarCategoriaUseCase: ActualizarCategoriaUseCase

    private val _items = MutableStateFlow<MutableList<CategoriaDTO>>(mutableListOf())
    val items: StateFlow<List<CategoriaDTO>> = _items.asStateFlow()

    private val _selected = MutableStateFlow<String?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarCategoriasUseCase = ListarCategoriasUseCase(categoriaRepositorio, almacenDatos)
        actualizarCategoriaUseCase = ActualizarCategoriaUseCase(categoriaRepositorio, almacenDatos)
        viewModelScope.launch {
            var items = listarCategoriasUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items)
        }
    }

    fun setSelectedCategoria(item: String?) {
        _selected.value = item
    }

    fun update(formState: CategoriaFormState) {
        val command = ActualizarCategoriaCommand(
            formState.nombre,
            selected.value!!,
            formState.imagePath
        )
        viewModelScope.launch {
            val item = actualizarCategoriaUseCase.invoke(command)
            _items.update { current ->
                current.map { if (it.id == item.id) item else it } as MutableList<CategoriaDTO>
            }
        }
    }
}
