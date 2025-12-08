package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ListarProductosUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClienteCategoriaMainViewModel(
    private val productoRepositorio: IProductoRepositorio,
    val almacenDatos: AlmacenDatos
): ViewModel() {

    private val _uiState = MutableStateFlow(ClienteCategoriaMainState())
    val uiState: StateFlow<ClienteCategoriaMainState> = _uiState

    private val _items = MutableStateFlow<MutableList<ProductoDTO>>(mutableListOf())
    val items: StateFlow<List<ProductoDTO>> = _items.asStateFlow()

    private val _selected = MutableStateFlow<String?>(null)
    val selected = _selected.asStateFlow()


    private val listarProductosUseCase: ListarProductosUseCase =
        ListarProductosUseCase(productoRepositorio, almacenDatos)

    init {
        viewModelScope.launch {
            val productos = listarProductosUseCase.invoke()
            _uiState.value = _uiState.value.copy(
                productos = productos,
                productosFiltrados = productos
            )
        }

    }

    fun filtrarPorCategoria(idCategoria: String?) {
        val todos = _uiState.value.productos

        val filtrados = if (idCategoria == null) {
            todos
        } else {
            todos.filter { it.idCategoria == idCategoria }
        }

        _uiState.value = _uiState.value.copy(
            categoriaId= idCategoria,
            productosFiltrados = filtrados
        )
    }

    fun selectCategoria(id: String?) {
        _selected.value = id
    }


}