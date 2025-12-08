package ies.sequeros.com.dam.pmdm.administrador.ui.listado

import androidx.lifecycle.ViewModel
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.ListarPedidosUseCase
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

class PedidosViewModel(
    private val pedidoRepositorio: IPedidoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    private val productoRepositorio: IProductoRepositorio,
    private val almacenDatos: AlmacenDatos
): ViewModel() {

    //private val listarPedidosUseCase: ListarPedidosUseCase


}