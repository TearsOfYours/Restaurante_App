package ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido

import androidx.lifecycle.ViewModel
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

class TerminarPedidoViewModel(
    private val productoRepositorio: IProductoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    private val pedidoRepositorio: IPedidoRepositorio,
    private val almacenDatos: AlmacenDatos
): ViewModel() {

}