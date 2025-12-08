package ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.ListarPedidosUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos.crearPedido.CreaPedidoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RealizarPedidoViewModel(
    private val productoRepositorio: IProductoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    private val pedidoRepositorio: IPedidoRepositorio,
    private val almacenDatos: AlmacenDatos
): ViewModel() {

    private val crearPedidoUseCase: CreaPedidoUseCase
    private val listarPedidosUseCase: ListarPedidosUseCase

    private val _items = MutableStateFlow<MutableList<PedidoDTO>>(mutableListOf())
    val items: StateFlow<List<PedidoDTO>> = _items.asStateFlow()

    private val _selected = MutableStateFlow<PedidoDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarPedidosUseCase = ListarPedidosUseCase(pedidoRepositorio)
        crearPedidoUseCase = CreaPedidoUseCase(pedidoRepositorio)
        viewModelScope.launch {
            var items = listarPedidosUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items.map { it.toDTO() })
        }
    }

}