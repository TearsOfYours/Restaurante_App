package ies.sequeros.com.dam.pmdm.administrador.ui.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar.ActivarCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar.ActivarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar.ActualizarProductoUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.BorrarProductoUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.activar.ActivarProductoCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.activar.ActivarProductoUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear.CrearProductoCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear.CrearProductoUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.actualizar.ActualizarProductoCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ListarProductosUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.form.ProductoFormState
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val productoRepositorio: IProductoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    val almacenDatos: AlmacenDatos,
) : ViewModel() {

    private val listarProductosUseCase: ListarProductosUseCase
    private val crearProductoUseCase: CrearProductoUseCase
    private val actualizarProductoUseCase: ActualizarProductoUseCase
    private val borrarProductoUseCase: BorrarProductoUseCase
    private val activarProductoUseCase: ActivarProductoUseCase
    private val _items = MutableStateFlow<MutableList<ProductoDTO>>(mutableListOf())
    val items: StateFlow<List<ProductoDTO>> = _items.asStateFlow()

    private val _selected = MutableStateFlow<ProductoDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarProductosUseCase = ListarProductosUseCase(productoRepositorio, almacenDatos)
        crearProductoUseCase = CrearProductoUseCase(productoRepositorio, categoriaRepositorio,almacenDatos)
        actualizarProductoUseCase = ActualizarProductoUseCase(productoRepositorio, almacenDatos)
        borrarProductoUseCase = BorrarProductoUseCase(productoRepositorio, almacenDatos)
        activarProductoUseCase = ActivarProductoUseCase(productoRepositorio,almacenDatos)
        viewModelScope.launch {
            val productos = listarProductosUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(productos)
        }
    }

    fun setSelectedProducto(item: ProductoDTO?) {
        _selected.value = item
    }

    fun add(state: ProductoFormState) {
        val command = CrearProductoCommand(
            name = state.nombre,
            precio = state.precio,
            idCategoria = state.idCategoria,
            imagePath = state.imagePath
        )

        viewModelScope.launch {
            try {
                val producto = crearProductoUseCase.invoke(command)
                _items.value = (_items.value + producto) as MutableList<ProductoDTO>
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun update(state: ProductoFormState) {
        val command = ActualizarProductoCommand(
            id = selected.value!!.id,
            name = state.nombre,
            precio = state.precio,
            idCategoria = state.idCategoria,
            imagePath = state.imagePath
        )

        viewModelScope.launch {
            val actualizado = actualizarProductoUseCase.invoke(command)
            _items.update { current ->
                current.map { if (it.id == actualizado.id) actualizado else it }
                    .toMutableList()
            }
        }
    }

    fun delete(item: ProductoDTO) {
        viewModelScope.launch {
            borrarProductoUseCase.invoke(item.id)
            _items.update { current ->
                current.filterNot { it.id == item.id }.toMutableList()
            }
        }
    }

    fun save(state: ProductoFormState) {
        if (_selected.value == null)
            add(state)
        else
            update(state)
    }
    fun switchEnableProducto(item: ProductoDTO) {
        val command= ActivarProductoCommand(
            item.id,
            !item.enabled,
        )

        viewModelScope.launch {
            val item=activarProductoUseCase.invoke(command)

            _items.value = _items.value.map {
                if (item.id == it.id)
                    item
                else
                    it
            } as MutableList<ProductoDTO>
        }

    }
}
